package com.iamdinuth.commonmicroservice.graphql.resolver;

import com.iamdinuth.commonmicroservice.data.entity.Country;
import com.iamdinuth.commonmicroservice.data.entity.Region;
import com.iamdinuth.commonmicroservice.data.service.CountryService;
import com.iamdinuth.commonmicroservice.data.service.RegionService;
import com.netflix.graphql.dgs.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@DgsComponent
public class RegionResolver {
    @Autowired
    private CountryService countryService;
    @Autowired
    private RegionService regionService;

    // Must Have DgsEntityFetcher for every Entity
    @DgsEntityFetcher(name = "Region")
    public Region region(Map<String, Object> values, DgsDataFetchingEnvironment dfe) {
        return regionService.findRegionById(UUID.fromString((String) values.get("id")), dfe);
    }
    @DgsQuery
    @RolesAllowed("admin-user")
    public List<Region> findRegionsForCountry(@InputArgument String countryCode, DgsDataFetchingEnvironment dfe) {
        return regionService.findRegionsForCountry(countryCode, dfe);
    }

    @DgsQuery
    @RolesAllowed("admin-user")
    public Region findRegionById(@InputArgument UUID id, DgsDataFetchingEnvironment dfe) {
        return regionService.findRegionById(id, dfe);
    }

    @DgsData(parentType = "Region", field = "country")
    public Country country(DgsDataFetchingEnvironment dfe) {
        Region region = dfe.getSource();
        if (region.getCountry() == null){
            return countryService.findCountryByRegionsId(region.getId(), dfe);
        } else {
            return region.getCountry();
        }
    }
}
