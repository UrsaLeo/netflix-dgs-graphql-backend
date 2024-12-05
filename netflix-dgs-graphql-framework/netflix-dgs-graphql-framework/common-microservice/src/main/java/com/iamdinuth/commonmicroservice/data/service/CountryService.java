package com.iamdinuth.commonmicroservice.data.service;

import com.iamdinuth.commonmicroservice.data.entity.Country;
import com.iamdinuth.commonmicroservice.data.repository.AutowireRepositories;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CountryService extends AutowireRepositories {
    public List<Country> findAll(DgsDataFetchingEnvironment dfe) {
        return countryRepository.findAll(egb.build(dfe));
    }

    public Country findCountryByRegionsId(UUID id, DgsDataFetchingEnvironment dfe){
        Optional<Country> countryByRegionId = countryRepository.findCountryByRegions_Id(id, egb.build(dfe));
        if (countryByRegionId.isPresent()){
            return countryByRegionId.get();
        } else {
            return null;
        }
    }

    public Country findByCode(String code, DgsDataFetchingEnvironment dfe) {
        Optional<Country> countryByCode = countryRepository.findByCode(code, egb.build(dfe));
        if (countryByCode.isPresent()){
            return countryByCode.get();
        } else {
            return null;
        }
    }
}
