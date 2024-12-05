package com.iamdinuth.jobmicroservice.graphql.directive;

import com.iamdinuth.jobmicroservice.config.keycloak.KeycloakUserData;
import com.iamdinuth.jobmicroservice.exception.AccessDeniedError;
import com.iamdinuth.jobmicroservice.graphql.context.Context;
import com.netflix.graphql.dgs.DgsDirective;
import com.netflix.graphql.dgs.context.DgsContext;
import graphql.execution.DataFetcherResult;
import graphql.execution.ResultPath;
import graphql.language.SourceLocation;
import graphql.schema.*;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;

import java.util.List;

@DgsDirective(name = "auth")
public class AuthDirective implements SchemaDirectiveWiring {
    public static final String DIRECTIVE_NAME = "auth";
    public static final String DIRECTIVE_ATTR_ROLE = "role";
    public static final String DIRECTIVE_ATTR_ERROR = "throwError";
    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> env) {

        GraphQLFieldsContainer fieldsContainer = env.getFieldsContainer();
        GraphQLFieldDefinition fieldDefinition = env.getFieldDefinition();

        FieldCoordinates fieldCoordinates = FieldCoordinates.coordinates(fieldsContainer.getName(), fieldDefinition.getName());


        DataFetcher<?> originalDataFetcher = env.getCodeRegistry().getDataFetcher(fieldCoordinates, fieldDefinition);
        DataFetcher<?> dataFetcher = DataFetcherFactories.wrapDataFetcher(
                originalDataFetcher,
                (dataFetchingEnvironment, value) -> {
                    String role = dataFetchingEnvironment.getFieldDefinition().getAppliedDirective(DIRECTIVE_NAME).getArgument(DIRECTIVE_ATTR_ROLE).getValue();
                    Boolean throwError = dataFetchingEnvironment.getFieldDefinition().getAppliedDirective(DIRECTIVE_NAME).getArgument(DIRECTIVE_ATTR_ERROR).getValue();
                    Context customContext = DgsContext.getCustomContext(dataFetchingEnvironment);
                    KeycloakUserData keycloakUserData = customContext.getKeycloakUserData();
                    if (keycloakUserData != null && keycloakUserData.getRoles() != null && keycloakUserData.getRoles().contains(role)) {
                        return value;
                    } else {
                        if (throwError){
                            List<SourceLocation> locations =  List.of(dataFetchingEnvironment.getField().getSourceLocation());
                            ResultPath path = dataFetchingEnvironment.getExecutionStepInfo().getPath();
                            DataFetcherResult.Builder<Object> resultBuilder = DataFetcherResult.newResult();
                            return resultBuilder.error(new AccessDeniedError(locations, path)).build();
                        } else {
                            return null;
                        }
                    }
                }
        );

//        env.getCodeRegistry().dataFetcher(fieldsContainer, fieldDefinition, dataFetcher);
        env.getCodeRegistry().dataFetcher(fieldCoordinates, dataFetcher);
        return fieldDefinition;
    }
}
