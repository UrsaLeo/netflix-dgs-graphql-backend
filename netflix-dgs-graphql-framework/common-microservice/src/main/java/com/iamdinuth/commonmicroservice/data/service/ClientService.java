package com.iamdinuth.commonmicroservice.data.service;

import com.iamdinuth.commonmicroservice.data.entity.Client;
import com.iamdinuth.commonmicroservice.data.repository.AutowireRepositories;
import com.iamdinuth.commonmicroservice.exception.BadInputError;
import com.iamdinuth.commonmicroservice.data.types.ClientInput;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import graphql.execution.ResultPath;
import org.springframework.stereotype.Service;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService extends AutowireRepositories {

    public Client saveClient(ClientInput clientInput, DgsDataFetchingEnvironment dfe) throws BadInputError {
        if (clientInput.getId() == null){
            // Create Client
            Client client = new Client();
            client.setName(clientInput.getName());
            client.setAddress(clientInput.getAddress());
            return clientRepository.save(client);
        }
        else {
            // Update client
            Optional<Client> clientById = clientRepository.findById(clientInput.getId());
            if (clientById.isPresent()){
                Client client = clientById.get();
                client.setName(clientInput.getName());
                client.setAddress(clientInput.getAddress());
                return clientRepository.save(client);
            } else {
                List<SourceLocation> locations =  List.of(dfe.getField().getSourceLocation());
                ResultPath path = dfe.getExecutionStepInfo().getPath();
                throw new BadInputError("Bad Input: clientInput.id", locations, path);
            }
        }
    }

    public List<Client> findAll(DgsDataFetchingEnvironment dfe) {
        return clientRepository.findAll(egb.build(dfe));
    }

    public Client findClientById(UUID id, DgsDataFetchingEnvironment dfe) {
        Optional<Client> clientById = clientRepository.findById(id, egb.build(dfe));
        if (clientById.isPresent()){
            return clientById.get();
        } else {
            return null;
        }
    }
}
