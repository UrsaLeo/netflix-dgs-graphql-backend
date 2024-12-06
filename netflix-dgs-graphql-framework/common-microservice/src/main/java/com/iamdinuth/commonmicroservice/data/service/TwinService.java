package com.iamdinuth.commonmicroservice.data.service;

import com.iamdinuth.commonmicroservice.data.entity.Twin;
import com.iamdinuth.commonmicroservice.data.repository.AutowireRepositories;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwinService extends AutowireRepositories {
    public List<Twin> findAll(DgsDataFetchingEnvironment dfe) {
        return twinRepository.findAll(egb.build(dfe));
    }

    public List<Twin> findTwinsForClient(String clientId, DgsDataFetchingEnvironment dfe) {
        return twinRepository.findTwinsForClient(clientId, egb.build(dfe));
    }
}
