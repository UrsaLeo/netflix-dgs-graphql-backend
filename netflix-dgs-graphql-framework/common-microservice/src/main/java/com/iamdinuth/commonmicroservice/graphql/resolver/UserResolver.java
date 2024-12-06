package com.iamdinuth.commonmicroservice.graphql.resolver;

import com.iamdinuth.commonmicroservice.data.entity.User;
import com.iamdinuth.commonmicroservice.data.service.UserService;
import com.iamdinuth.commonmicroservice.graphql.context.Context;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.context.DgsContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@DgsComponent
@Slf4j
public class UserResolver {
    @Autowired
    private UserService userService;

    @DgsQuery
    public List<User> findAllUsers(DgsDataFetchingEnvironment dfe) {
        Context customContext = DgsContext.getCustomContext(dfe);
        log.debug(customContext.getKeycloakUserData().toString());
        return userService.findAll(dfe);
    }

    @DgsQuery
//    @RolesAllowed("admin-user")
    public List<User> findUsersforClient(@InputArgument String clientId, DgsDataFetchingEnvironment dfe) {
        return userService.findUsersForClient(clientId, dfe);
    }
}
