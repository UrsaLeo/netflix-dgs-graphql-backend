package com.iamdinuth.commonmicroservice.data.service;

import com.iamdinuth.commonmicroservice.data.entity.Client;
import com.iamdinuth.commonmicroservice.data.repository.AutowireRepositories;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService extends AutowireRepositories {

//    public Client putJob(ClientInput clientInput, DgsDataFetchingEnvironment dfe) throws BadInputError {
//        if (clientInput.getId() == null){
//            // Create Job
//            BatchProperties.Client client = new Client();
//            job.setTitle(jobInput.getTitle());
//            job.setDescription(jobInput.getDescription());
//            job.setJobStatus(jobInput.getJobStatus());
//            return jobRepository.save(job);
//        } else {
//            // Update Job
//            Optional<Job> jobById = jobRepository.findById(jobInput.getId());
//            if (jobById.isPresent()){
//                Job job = jobById.get();
//                job.setTitle(jobInput.getTitle());
//                job.setDescription(jobInput.getDescription());
//                job.setJobStatus(jobInput.getJobStatus());
//                return jobRepository.save(job);
//            } else {
//                List<SourceLocation> locations =  List.of(dfe.getField().getSourceLocation());
//                ResultPath path = dfe.getExecutionStepInfo().getPath();
//                throw new BadInputError("Bad Input: jobInput.id", locations, path);
//            }
//        }
//    }
    public List<Client> findAll(DgsDataFetchingEnvironment dfe) {
        return clientRepository.findAll(egb.build(dfe));
    }

    public Client findClientById(String id, DgsDataFetchingEnvironment dfe) {
        Optional<Client> clientById = clientRepository.findById(id, egb.build(dfe));
        if (clientById.isPresent()){
            return clientById.get();
        } else {
            return null;
        }
    }
}
