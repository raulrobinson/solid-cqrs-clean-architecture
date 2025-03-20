package com.example.ws.application.usecases;

import com.example.ws.application.dto.ClientRequestDto;
import com.example.ws.domain.model.ClientDomain;
import com.example.ws.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateClientUseCase {

    private final ClientRepository clientRepository;

    public CreateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDomain execute(ClientRequestDto client) {
        return clientRepository.create(client);
    }

}
