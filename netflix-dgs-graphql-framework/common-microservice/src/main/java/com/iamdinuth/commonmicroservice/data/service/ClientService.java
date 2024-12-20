package com.iamdinuth.commonmicroservice.data.service;

import com.iamdinuth.commonmicroservice.data.entity.Client;
import com.iamdinuth.commonmicroservice.data.repository.AutowireRepositories;
import com.iamdinuth.commonmicroservice.data.types.MutationResponse;
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

    public MutationResponse deleteClient(UUID id, DgsDataFetchingEnvironment dfe){
        try {
            // Check if the client exists
            Client client = clientRepository.findById(id).orElse(null);
            if (client == null) {
                return new MutationResponse(false, "Client not found.", 404);
            }
            // Delete the client
            clientRepository.delete(client);
            return new MutationResponse(true, "Client deleted successfully.", 200);
        } catch (Exception e) {
            return new MutationResponse(false, "Error deleting client: " + e.getMessage(), 500);
        }
    }
}
