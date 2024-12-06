package com.iamdinuth.commonmicroservice.graphql.resolver;

import com.iamdinuth.commonmicroservice.data.entity.Client;
import com.iamdinuth.commonmicroservice.data.service.ClientService;
import com.iamdinuth.commonmicroservice.graphql.context.Context;
import com.iamdinuth.commonmicroservice.graphql.generated.types.ClientInput;
import com.iamdinuth.commonmicroservice.exception.BadInputError;
import com.netflix.graphql.dgs.*;
import com.netflix.graphql.dgs.context.DgsContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@DgsComponent
@Slf4j
public class ClientResolver {

    @Autowired
    private ClientService clientService;

//    @DgsEntityFetcher(name = "Client")
//    public Client client(Map<String, Object> values, DgsDataFetchingEnvironment dfe) {
//        log.debug(values.toString());
//        return clientService.findClientById((String) values.get("id"), dfe);
//    }

    @DgsMutation
    public Client saveClient(@InputArgument ClientInput clientInput , DgsDataFetchingEnvironment dfe) throws BadInputError {
        return clientService.saveClient(clientInput, dfe);
    }

    @DgsQuery
    public Client findClientById(@InputArgument UUID id, DgsDataFetchingEnvironment dfe) {
        log.debug("Fetching client with ID: " + id);
        return clientService.findClientById(id, dfe);
    }

    @DgsQuery
    public List<Client> findAllClients(DgsDataFetchingEnvironment dfe) {
        Context customContext = DgsContext.getCustomContext(dfe);
        log.debug(customContext.getKeycloakUserData().toString());
        return clientService.findAll(dfe);
    }
}
