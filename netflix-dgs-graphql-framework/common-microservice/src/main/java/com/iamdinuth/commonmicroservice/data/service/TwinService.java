package com.iamdinuth.commonmicroservice.data.service;

import com.iamdinuth.commonmicroservice.data.entity.Client;
import com.iamdinuth.commonmicroservice.data.entity.Twin;
import com.iamdinuth.commonmicroservice.data.repository.AutowireRepositories;
import com.iamdinuth.commonmicroservice.exception.BadInputError;
import com.iamdinuth.commonmicroservice.data.types.ClientIdInput;
import com.iamdinuth.commonmicroservice.data.types.MutationResponse;
import com.iamdinuth.commonmicroservice.data.types.TwinInput;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import graphql.execution.ResultPath;
import graphql.language.SourceLocation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TwinService extends AutowireRepositories {
    public List<Twin> findAll(DgsDataFetchingEnvironment dfe) {
        return twinRepository.findAll(egb.build(dfe));
    }

    public Twin saveTwin(TwinInput twinInput, DgsDataFetchingEnvironment dfe) throws BadInputError {
        if (twinInput.getTwinId() == null){
            // Create Twin
            ClientIdInput clientIdInput = twinInput.getClient();

            Client client = new Client();
            client.setId(clientIdInput.getId());

            Twin twin = new Twin();
            twin.setTwinName(twinInput.getTwinName());
            twin.setDescription(twinInput.getDescription());
            twin.setLongitude(twinInput.getLongitude());
            twin.setLatitude(twinInput.getLatitude());
            twin.setUri(twinInput.getUri());
            twin.setClient(client);
            return twinRepository.save(twin);
        }
        else {
            // Update twin
            Optional<Twin> twinById = twinRepository.findById(twinInput.getTwinId());
            if (twinById.isPresent()){
                ClientIdInput clientIdInput = twinInput.getClient();

                Client client = new Client();
                client.setId(clientIdInput.getId());

                Twin twin =  twinById.get();
                twin.setTwinName(twinInput.getTwinName());
                twin.setDescription(twinInput.getDescription());
                twin.setLongitude(twinInput.getLongitude());
                twin.setLatitude(twinInput.getLatitude());
                twin.setUri(twinInput.getUri());
                twin.setClient(client);
                return twinRepository.save(twin);
            } else {
                List<SourceLocation> locations =  List.of(dfe.getField().getSourceLocation());
                ResultPath path = dfe.getExecutionStepInfo().getPath();
                throw new BadInputError("Bad Input: twinInput.twinId", locations, path);
            }
        }
    }

    public List<Twin> findTwinsForClient(UUID clientId, DgsDataFetchingEnvironment dfe) {
        return twinRepository.findTwinsForClient(clientId, egb.build(dfe));
    }

    public Twin findTwinById(UUID twinId, DgsDataFetchingEnvironment dfe) {
        Optional<Twin> twinById = twinRepository.findById(twinId, egb.build(dfe));
        if (twinById.isPresent()){
            return twinById.get();
        } else {
            return null;
        }
    }

    public MutationResponse deleteTwin(UUID twinId, DgsDataFetchingEnvironment dfe){
        try {
            // Check if the Twin exists
            Twin twin = twinRepository.findById(twinId).orElse(null);
            if (twin == null) {
                return new MutationResponse(false, "Twin not found.", 404);
            }
            // Delete the Twin
            twinRepository.delete(twin);
            return new MutationResponse(true, "Twin deleted successfully.", 200);
        } catch (Exception e) {
            return new MutationResponse(false, "Error deleting twin: " + e.getMessage(), 500);
        }
    }
}
