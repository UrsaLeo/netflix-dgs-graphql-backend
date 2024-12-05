package com.iamdinuth.jobmicroservice.config.keycloak;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class KeycloakUserData implements Serializable {
    @NonNull
    private String username;
    private String email;
    private Set<String> roles;
}
