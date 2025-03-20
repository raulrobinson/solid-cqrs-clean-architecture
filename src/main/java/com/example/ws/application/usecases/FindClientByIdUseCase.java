package com.example.ws.application.usecases;

import com.example.ws.domain.model.ClientDomain;
import com.example.ws.domain.repository.ClientRepository;
import com.example.ws.infrastructure.shared.exception.NotFoundException;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class FindClientByIdUseCase {

    private static final Logger transactionLogger = org.slf4j.LoggerFactory.getLogger(FindClientByIdUseCase.class);

    private final ClientRepository clientRepository;

    public FindClientByIdUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDomain handle(String clientCode) {
        ClientDomain client = clientRepository.getByClientCode(clientCode);
        if (client == null) throw new NotFoundException("Client with Client Code: " + clientCode + " not found");
        transactionLogger.info("Client with Client Code: {} found", clientCode);
        return client;
    }
}
