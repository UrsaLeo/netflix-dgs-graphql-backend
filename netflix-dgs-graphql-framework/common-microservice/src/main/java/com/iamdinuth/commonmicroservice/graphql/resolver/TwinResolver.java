package com.iamdinuth.commonmicroservice.graphql.resolver;

import com.iamdinuth.commonmicroservice.data.entity.Twin;
import com.iamdinuth.commonmicroservice.data.service.TwinService;
import com.iamdinuth.commonmicroservice.graphql.context.Context;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.context.DgsContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
//    @RolesAllowed("admin-user")
    public List<Twin> findTwinsforClient(@InputArgument String clientId, DgsDataFetchingEnvironment dfe) {
        return twinService.findTwinsForClient(clientId, dfe);
    }
}
