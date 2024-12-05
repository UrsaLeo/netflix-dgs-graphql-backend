package com.iamdinuth.commonmicroservice.graphql.context;

import com.iamdinuth.commonmicroservice.config.keycloak.KeycloakUserData;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Context {
    KeycloakUserData keycloakUserData;
}
