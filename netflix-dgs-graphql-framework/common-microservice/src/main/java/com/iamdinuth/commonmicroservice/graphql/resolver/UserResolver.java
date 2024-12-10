package com.iamdinuth.commonmicroservice.graphql.resolver;

import com.iamdinuth.commonmicroservice.data.entity.User;
import com.iamdinuth.commonmicroservice.data.service.UserService;
import com.iamdinuth.commonmicroservice.exception.BadInputError;
import com.iamdinuth.commonmicroservice.graphql.context.Context;
import com.iamdinuth.commonmicroservice.graphql.generated.types.MutationResponse;
import com.iamdinuth.commonmicroservice.graphql.generated.types.UserInput;
import com.netflix.graphql.dgs.*;
import com.netflix.graphql.dgs.context.DgsContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

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

    @DgsMutation
    @RolesAllowed("admin")
    public User saveUser(@InputArgument UserInput userInput , DgsDataFetchingEnvironment dfe) throws BadInputError {
        return userService.saveUser(userInput, dfe);
    }
    @DgsMutation
    @RolesAllowed("admin")
    public MutationResponse deleteUser(@InputArgument UUID userId, DgsDataFetchingEnvironment dfe) throws BadInputError{
        return userService.deleteUser(userId, dfe);
    }

    @DgsQuery
    @RolesAllowed("admin")
    public List<User> findUsersforClient(@InputArgument UUID clientId, DgsDataFetchingEnvironment dfe) {
        return userService.findUsersForClient(clientId, dfe);
    }
}
