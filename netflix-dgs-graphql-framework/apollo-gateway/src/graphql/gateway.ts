import { ApolloServer } from "apollo-server-express";
import { ApolloGateway, IntrospectAndCompose } from "@apollo/gateway";
import { microservices, graphqlPath, logging } from "../config/config";
import FileUploadDataSource from "@profusion/apollo-federation-upload"
import { FileUploadDataSourceArgs } from '@profusion/apollo-federation-upload/build/FileUploadDataSource';
import { graphqlUploadExpress } from "graphql-upload";
import * as log from "../utils/log"
import 'isomorphic-fetch';

const logger = log.getLogger("gateway")

class RequestDataSource extends FileUploadDataSource {
    constructor(config?: FileUploadDataSourceArgs) {
        super(config);
    }

    willSendRequest = async ({ request, context }) => {
        if (context.req?.headers["authorization"]) {
            request.http?.headers.set("Authorization", context.req?.headers["authorization"]);
        }
        if (context.req?.headers["x-auth-key"]) {
            request.http?.headers.set("x-auth-key", context.req?.headers["x-auth-key"]);
        }
    };
}

const sleep = (milliseconds) => {
    const date = Date.now();
    let currentDate = null;
    do {
        currentDate = Date.now();
    } while (currentDate - date < milliseconds);
}

export const waitForServiceAvailability = async (services) => {
    const heathCheckRequests = services.map(
        (service) =>
            new Promise(async (resolve) => {
                logger.info(`Checking ${service.name} health: `, `${service.url.replace(graphqlPath, ``)}/actuator`);
                await serviceAvailability(service);
                resolve(service);
            })
    );
    await Promise.all(heathCheckRequests).then((result) => result.filter(Boolean));
}

export const serviceAvailability = async (service) => {
    let health = false;
    do {
        await fetch(`${service.url.replace(graphqlPath, ``)}/actuator`)
            .then((res) => {
                if (res.ok) return res.json
                else throw new Error();
            })
            .then(() => {
                logger.info(`Health verified: ${service.name}`)
                health = true;
            })
            .catch(() => {
                logger.error(`Unable to reach: ${service.name}. Retry in 10s.`)
                sleep(10000)
            });
    } while(!health);
}

export const startApolloGateway = async (app) => {
    // Upload config
    app.use(graphqlUploadExpress());

    const serviceList = [];
    logger.info("Microservices: ")
    logger.info(microservices);
    Object.keys(microservices).forEach((serviceName) => {
        if (microservices[serviceName].toUpperCase() != "DISABLED") {
            serviceList.push({name: serviceName, url: microservices[serviceName]});
        }
    });
    await waitForServiceAvailability(serviceList);
    const gateway = new ApolloGateway({
        pollIntervalInMs: 10000,
        supergraphSdl: new IntrospectAndCompose({
            subgraphs: serviceList
        }),
        debug: logging.apollo_logging,
        buildService: ({ url }) => new RequestDataSource({ url, useChunkedTransfer: true }),
    })
    const server = new ApolloServer({
        gateway,
        context: ({ req }) => {
            return {
                req
            }
        }
    });
    await server.start();
    server.applyMiddleware({
        app,
        path: graphqlPath
    })
}