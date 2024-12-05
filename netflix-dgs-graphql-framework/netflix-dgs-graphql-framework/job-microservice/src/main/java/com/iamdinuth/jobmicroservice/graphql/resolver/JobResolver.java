package com.iamdinuth.jobmicroservice.graphql.resolver;

import com.iamdinuth.jobmicroservice.data.entity.Job;
import com.iamdinuth.jobmicroservice.data.service.JobService;
import com.iamdinuth.jobmicroservice.exception.BadInputError;
import com.iamdinuth.jobmicroservice.graphql.context.Context;
import com.iamdinuth.jobmicroservice.graphql.generated.types.Country;
import com.iamdinuth.jobmicroservice.graphql.generated.types.JobInput;
import com.iamdinuth.jobmicroservice.graphql.generated.types.Region;
import com.netflix.graphql.dgs.*;
import com.netflix.graphql.dgs.context.DgsContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@DgsComponent
@Slf4j
public class JobResolver {
    @Autowired
    private JobService jobService;

    // Must Have DgsEntityFetcher for every Entity
    @DgsEntityFetcher(name = "Job")
    public Job job(Map<String, Object> values, DgsDataFetchingEnvironment dfe) {
        log.debug(values.toString());
        return jobService.findById(UUID.fromString((String) values.get("id")), dfe);
    }

    @DgsData(parentType = "Job")
    public Country country(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        Job job = dataFetchingEnvironment.getSource();
        if (job.getCountryCode() != null){
            return new Country(job.getCountryCode());
        } else {
            return null;
        }
    }

    @DgsData(parentType = "Job")
    public Region region(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        Job job = dataFetchingEnvironment.getSource();
        if (job.getRegionId() != null){
            return new Region(job.getRegionId());
        } else {
            return null;
        }
    }
    @DgsQuery
    public List<Job> findAllJobs(DgsDataFetchingEnvironment dfe) {
        Context customContext = DgsContext.getCustomContext(dfe);
        log.debug(customContext.getKeycloakUserData().toString());
        return jobService.findAll(dfe);
    }

    @DgsMutation
    public Job putJob(@InputArgument JobInput jobInput, DgsDataFetchingEnvironment dfe) throws BadInputError {
        return jobService.putJob(jobInput, dfe);
    }
}
