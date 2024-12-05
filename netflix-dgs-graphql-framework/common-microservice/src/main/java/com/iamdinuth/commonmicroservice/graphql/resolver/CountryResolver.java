package com.iamdinuth.commonmicroservice.graphql.resolver;

import com.iamdinuth.commonmicroservice.data.entity.Country;
import com.iamdinuth.commonmicroservice.data.entity.Region;
import com.iamdinuth.commonmicroservice.data.service.CountryService;
import com.iamdinuth.commonmicroservice.data.service.RegionService;
import com.iamdinuth.commonmicroservice.graphql.context.Context;
import com.netflix.graphql.dgs.*;
import com.netflix.graphql.dgs.context.DgsContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@DgsComponent
@Slf4j
public class CountryResolver {
    @Autowired
    private CountryService countryService;
    @Autowired
    private RegionService regionService;

    // Must Have DgsEntityFetcher for every Entity
    @DgsEntityFetcher(name = "Country")
    public Country country(Map<String, Object> values, DgsDataFetchingEnvironment dfe) {
        log.debug(values.toString());
        return countryService.findByCode((String) values.get("code"), dfe);
    }
    @DgsQuery
    public List<Country> findAllCountries(DgsDataFetchingEnvironment dfe) {
        Context customContext = DgsContext.getCustomContext(dfe);
        log.debug(customContext.getKeycloakUserData().toString());
        return countryService.findAll(dfe);
    }


    @DgsData(parentType = "Country", field = "regions")
    public List<Region> regions(DgsDataFetchingEnvironment dfe) {
        Country country = dfe.getSource();
        if (country.getRegions() == null){
            return regionService.findRegionsForCountry(country.getCode(), dfe);
        } else {
            return country.getRegions();
        }
    }
    @DgsData(parentType = "Country", field = "president")
    public String president(DgsDataFetchingEnvironment dfe) {
        Country country = dfe.getSource();
        Context customContext = DgsContext.getCustomContext(dfe);
        log.debug(customContext.getKeycloakUserData().toString());
        return customContext.getKeycloakUserData().getUsername();
    }
}
