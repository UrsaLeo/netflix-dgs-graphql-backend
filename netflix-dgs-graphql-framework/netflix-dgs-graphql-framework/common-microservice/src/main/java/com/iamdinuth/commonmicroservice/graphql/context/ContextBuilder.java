package com.iamdinuth.commonmicroservice.graphql.context;

import com.iamdinuth.commonmicroservice.config.keycloak.KeycloakUserData;
import com.netflix.graphql.dgs.context.DgsCustomContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContextBuilder implements DgsCustomContextBuilder<Context> {

    @Autowired
    KeycloakUserData keycloakUserData;
    @Override
    public Context build() {
        return new Context(keycloakUserData);
    }
}
