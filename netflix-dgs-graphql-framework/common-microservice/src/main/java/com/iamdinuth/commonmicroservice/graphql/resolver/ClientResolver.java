package com.iamdinuth.commonmicroservice.graphql.resolver;

import com.iamdinuth.commonmicroservice.data.entity.Client;
import com.iamdinuth.commonmicroservice.data.service.ClientService;
import com.iamdinuth.commonmicroservice.graphql.context.Context;
import com.netflix.graphql.dgs.*;
import com.netflix.graphql.dgs.context.DgsContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    @DgsQuery
    public Client findClientById(@InputArgument String id, DgsDataFetchingEnvironment dfe) {
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
