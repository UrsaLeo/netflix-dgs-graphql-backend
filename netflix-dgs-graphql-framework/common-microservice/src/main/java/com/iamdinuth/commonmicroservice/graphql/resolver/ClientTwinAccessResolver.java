package com.iamdinuth.commonmicroservice.graphql.resolver;

import com.iamdinuth.commonmicroservice.data.entity.ClientTwinAccess;
import com.iamdinuth.commonmicroservice.data.entity.Twin;
import com.iamdinuth.commonmicroservice.data.service.ClientTwinAccessService;
import com.iamdinuth.commonmicroservice.data.types.MutationResponse;
import com.iamdinuth.commonmicroservice.exception.BadInputError;
import com.iamdinuth.commonmicroservice.graphql.generated.types.AssignUserToTwinInput;
import com.netflix.graphql.dgs.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@DgsComponent
@Slf4j
public class ClientTwinAccessResolver {

    @Autowired
    private ClientTwinAccessService clientTwinAccessService;

    @DgsMutation
    public ClientTwinAccess assignUserToTwin(@InputArgument AssignUserToTwinInput assignUserToTwinInput) {
        return clientTwinAccessService.assignUserToTwin(assignUserToTwinInput.getTwinId(), assignUserToTwinInput.getUserEmail(), assignUserToTwinInput.getRoleDescription());
    }

    @DgsMutation
//    @RolesAllowed("admin")
    public MutationResponse unassignedUser(@InputArgument UUID id, DgsDataFetchingEnvironment dfe) throws BadInputError {
        return clientTwinAccessService.deleteAssignedUserForTwin(id,dfe);
    }

    @DgsQuery
//    @RolesAllowed("admin")
    public List<ClientTwinAccess> findAssinedUsersForTwin(@InputArgument UUID twinId, DgsDataFetchingEnvironment dfe) {
        return clientTwinAccessService.findAssinedUsersForTwin(twinId, dfe);
    }


}
