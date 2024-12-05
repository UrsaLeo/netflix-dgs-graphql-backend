package com.iamdinuth.commonmicroservice.config.keycloak;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;

import java.util.Set;

@Configuration
public class KeycloakConfig {

    @Value("${keycloak.resource}")
    private String keycloakClientId;
    @Value("${keycloak.use-resource-role-mappings}")
    private Boolean keycloakUseResourceRoleMappings;
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Bean
    public KeycloakUserData userDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof KeycloakPrincipal){
            KeycloakPrincipal principal = (KeycloakPrincipal) auth.getPrincipal();
            KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
            AccessToken accessToken = session.getToken();
            Set<String> roles;
            if (keycloakUseResourceRoleMappings) {
                roles = accessToken.getResourceAccess().get(keycloakClientId).getRoles();
            } else {
                roles = accessToken.getRealmAccess().getRoles();
            }
            String username = accessToken.getPreferredUsername();
            String email = accessToken.getEmail();
            return new KeycloakUserData(username,email,roles);
        }
        else {
            return new KeycloakUserData((String) auth.getPrincipal()); //anonymousUser
        }
    }
}
