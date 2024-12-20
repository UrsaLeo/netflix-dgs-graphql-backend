package com.iamdinuth.commonmicroservice.graphql.resolver;

import com.iamdinuth.commonmicroservice.data.entity.Client;
import com.iamdinuth.commonmicroservice.data.entity.Twin;
import com.iamdinuth.commonmicroservice.data.service.TwinService;
import com.iamdinuth.commonmicroservice.exception.BadInputError;
import com.iamdinuth.commonmicroservice.graphql.context.Context;
import com.iamdinuth.commonmicroservice.data.types.MutationResponse;
import com.iamdinuth.commonmicroservice.data.types.TwinInput;
import com.netflix.graphql.dgs.*;
import com.netflix.graphql.dgs.context.DgsContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

@DgsComponent
@Slf4j
public class TwinResolver {

    @Autowired
    private TwinService twinService;

    @DgsQuery
    public List<Twin> findAllTwins(DgsDataFetchingEnvironment dfe) {
        Context customContext = DgsContext.getCustomContext(dfe);
        log.debug(customContext.getKeycloakUserData().toString());
        return twinService.findAll(dfe);
    }

    @DgsQuery
//    @RolesAllowed("admin")
    public Twin findTwinById(@InputArgument UUID twinId, DgsDataFetchingEnvironment dfe) {
        log.debug("Fetching twin with ID: " + twinId);
        return twinService.findTwinById(twinId, dfe);
    }

    @DgsMutation
//    @RolesAllowed("admin")
    public Twin saveTwin(@InputArgument TwinInput twinInput , DgsDataFetchingEnvironment dfe) throws BadInputError {
        return twinService.saveTwin(twinInput, dfe);
    }

    @DgsMutation
//    @RolesAllowed("admin")
    public MutationResponse deleteTwin(@InputArgument UUID twinId, DgsDataFetchingEnvironment dfe) throws BadInputError{
        return twinService.deleteTwin(twinId, dfe);
    }

    @DgsQuery
//    @RolesAllowed("admin")
    public List<Twin> findTwinsforClient(@InputArgument UUID clientId, DgsDataFetchingEnvironment dfe) {
        return twinService.findTwinsForClient(clientId, dfe);
    }
}
