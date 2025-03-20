package com.example.ws.application.usecases;

import com.example.ws.application.dto.PartialClientDto;
import com.example.ws.domain.model.ClientDomain;
import com.example.ws.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class PartialUpdateClientUseCase {

    private final ClientRepository clientRepository;

    public PartialUpdateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDomain execute(String clientCode, PartialClientDto client) {
        return clientRepository.partialUpdate(clientCode, client);
    }
}
