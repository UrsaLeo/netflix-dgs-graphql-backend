package com.iamdinuth.jobmicroservice.graphql.context;

import com.iamdinuth.jobmicroservice.config.keycloak.KeycloakUserData;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Context {
    KeycloakUserData keycloakUserData;
}
