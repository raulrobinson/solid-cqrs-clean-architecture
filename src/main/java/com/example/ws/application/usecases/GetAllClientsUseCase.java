package com.example.ws.application.usecases;

import com.example.ws.domain.model.ClientDomain;
import com.example.ws.domain.repository.ClientRepository;
import com.example.ws.infrastructure.shared.exception.NoContentException;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllClientsUseCase {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(GetAllClientsUseCase.class);

    private final ClientRepository clientRepository;

    public GetAllClientsUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDomain> handle() {
        List<ClientDomain> clients = clientRepository.getAll();
        if (clients.isEmpty()) throw new NoContentException("No clients found");
        log.info("Found {} clients", clients.size());
        return clients;
    }
}
