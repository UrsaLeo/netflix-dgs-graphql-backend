import * as express from "express";
import { port, graphqlPath } from "./config/config";
import { startApolloGateway } from "./graphql/gateway";

const expressServer = async () => {
  const app = express();
  await startApolloGateway(app);
  app.listen({ port }, () => {
    console.log(`🚀 Server ready at http://localhost:${port}${graphqlPath}`);
  });
}

expressServer()