package com.iamdinuth.commonmicroservice.data.service;

import com.iamdinuth.commonmicroservice.data.entity.*;
import com.iamdinuth.commonmicroservice.data.repository.ClientTwinAccessRepository;
import com.iamdinuth.commonmicroservice.data.repository.RoleRepository;
import com.iamdinuth.commonmicroservice.data.repository.TwinRepository;
import com.iamdinuth.commonmicroservice.data.repository.AutowireRepositories;
import com.iamdinuth.commonmicroservice.data.repository.UserRepository;
import com.iamdinuth.commonmicroservice.data.types.MutationResponse;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class ClientTwinAccessService extends AutowireRepositories {

    @Autowired
    private ClientTwinAccessRepository clientTwinAccessRepository;

    @Autowired
    private TwinRepository twinRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<ClientTwinAccess> findAssinedUsersForTwin(UUID twinId, DgsDataFetchingEnvironment dfe) {
        return clientTwinAccessRepository.findAssinedUsersForTwin(twinId, egb.build(dfe));
    }

    public ClientTwinAccess findAssignedUserById(UUID id) {
        // Find the ClientTwinAccess entry by ID
        return clientTwinAccessRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

    public ClientTwinAccess assignUserToTwin(UUID twinId, String userEmail, String roleDescription) {
        // Find the twin
        Twin twin = twinRepository.findById(twinId)
                .orElseThrow(() -> new EntityNotFoundException("Twin not found with ID: " + twinId));

        // Find the user by email
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + userEmail));

        // Find the role by description
        Role role = roleRepository.findByDescription(roleDescription)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with description: " + roleDescription));

        // Optional: Retrieve client associated with the twin
        Client client = twin.getClient();

        boolean alreadyAssigned = clientTwinAccessRepository.existsByTwinAndUser(twin, user);
        if (alreadyAssigned) {
            throw new IllegalStateException(
                    String.format("User '%s' is already assigned to this twin.", userEmail)
            );
        }

        // Create and save the ClientTwinAccess entry
        ClientTwinAccess clientTwinAccess = new ClientTwinAccess();
        clientTwinAccess.setClient(client); // Can be null if no client is associated
        clientTwinAccess.setTwin(twin);
        clientTwinAccess.setUser(user);
        clientTwinAccess.setRole(role);

        return clientTwinAccessRepository.save(clientTwinAccess);
    }

    public ClientTwinAccess updateAccessLevelForUser(UUID id, String newRoleDescription) {
        // Find the existing ClientTwinAccess record by id
        ClientTwinAccess clientTwinAccess = clientTwinAccessRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User is not assigned to the Twin "));

        Role newRole = roleRepository.findByDescription(newRoleDescription)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with description: " + newRoleDescription));

        clientTwinAccess.setRole(newRole);

        return clientTwinAccessRepository.save(clientTwinAccess);
    }

    public MutationResponse deleteAssignedUserForTwin(UUID id, DgsDataFetchingEnvironment dfe){
        try {
            // Check if the Assigned User exists
            ClientTwinAccess clientTwinAccess = clientTwinAccessRepository.findById(id).orElse(null);
            if (clientTwinAccess == null) {
                return new MutationResponse(false, "User not found.", 404);
            }
            // UnAssigned the user
            clientTwinAccessRepository.delete(clientTwinAccess);
            return new MutationResponse(true, "User deleted successfully.", 200);
        } catch (Exception e) {
            return new MutationResponse(false, "Error deleting user: " + e.getMessage(), 500);
        }
    }
}
