package com.iamdinuth.jobmicroservice.data.service;

import com.iamdinuth.jobmicroservice.data.entity.Job;
import com.iamdinuth.jobmicroservice.data.repository.AutowireRepositories;
import com.iamdinuth.jobmicroservice.exception.AccessDeniedError;
import com.iamdinuth.jobmicroservice.exception.BadInputError;
import com.iamdinuth.jobmicroservice.graphql.generated.types.JobInput;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import graphql.execution.DataFetcherResult;
import graphql.execution.ResultPath;
import graphql.language.SourceLocation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobService extends AutowireRepositories {
    public List<Job> findAll(DgsDataFetchingEnvironment dfe) {
        return jobRepository.findAll(egb.build(dfe));
    }

    public Job putJob(JobInput jobInput, DgsDataFetchingEnvironment dfe) throws BadInputError {
        if (jobInput.getId() == null){
            // Create Job
            Job job = new Job();
            job.setTitle(jobInput.getTitle());
            job.setDescription(jobInput.getDescription());
            job.setJobStatus(jobInput.getJobStatus());
            return jobRepository.save(job);
        } else {
            // Update Job
            Optional<Job> jobById = jobRepository.findById(jobInput.getId());
            if (jobById.isPresent()){
                Job job = jobById.get();
                job.setTitle(jobInput.getTitle());
                job.setDescription(jobInput.getDescription());
                job.setJobStatus(jobInput.getJobStatus());
                return jobRepository.save(job);
            } else {
                List<SourceLocation> locations =  List.of(dfe.getField().getSourceLocation());
                ResultPath path = dfe.getExecutionStepInfo().getPath();
                throw new BadInputError("Bad Input: jobInput.id", locations, path);
            }
        }
    }

    public Job findById(UUID id, DgsDataFetchingEnvironment dfe) {
        Optional<Job> job = jobRepository.findById(id, egb.build(dfe));
        if (job.isEmpty()) return null;
        return job.get();
    }
}
