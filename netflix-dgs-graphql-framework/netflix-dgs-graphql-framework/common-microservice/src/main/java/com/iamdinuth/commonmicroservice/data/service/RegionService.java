package com.iamdinuth.commonmicroservice.data.service;

import com.iamdinuth.commonmicroservice.data.entity.Region;
import com.iamdinuth.commonmicroservice.data.repository.AutowireRepositories;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegionService extends AutowireRepositories {

    public List<Region> findRegionsForCountry(String countryCode, DgsDataFetchingEnvironment dfe) {
        return regionRepository.findRegionsForCountry(countryCode, egb.build(dfe));
    }

    public Region findRegionById(UUID id, DgsDataFetchingEnvironment dfe) {
        Optional<Region> region = regionRepository.findById(id, egb.build(dfe));
        if (region.isEmpty()) return null;
        return region.get();
    }
}
