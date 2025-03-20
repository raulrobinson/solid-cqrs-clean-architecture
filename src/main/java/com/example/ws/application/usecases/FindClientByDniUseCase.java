package com.example.ws.application.usecases;

import com.example.ws.domain.model.ClientDomain;
import com.example.ws.domain.repository.ClientRepository;
import com.example.ws.infrastructure.shared.exception.NotFoundException;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class FindClientByDniUseCase {

    private static final Logger transactionLogger = org.slf4j.LoggerFactory.getLogger(FindClientByDniUseCase.class);

    private final ClientRepository clientRepository;

    public FindClientByDniUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDomain handle(String dni) {
        ClientDomain client = clientRepository.getClientByDni(dni);
        if (client == null) throw new NotFoundException("Client with DNI: " + dni + " not found");
        transactionLogger.info("Client with DNI: {} found", dni);
        return client;
    }
}
