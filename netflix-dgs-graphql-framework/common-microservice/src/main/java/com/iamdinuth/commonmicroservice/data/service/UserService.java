package com.iamdinuth.commonmicroservice.data.service;

import com.iamdinuth.commonmicroservice.data.entity.User;
import com.iamdinuth.commonmicroservice.data.repository.AutowireRepositories;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AutowireRepositories {

    public List<User> findAll(DgsDataFetchingEnvironment dfe) {
        return userRepository.findAll(egb.build(dfe));
    }

    public List<User> findUsersForClient(String clientId, DgsDataFetchingEnvironment dfe) {
        return userRepository.findUsersForClient(clientId, egb.build(dfe));
    }


}
