package com.example.ws.application.usecases;

import com.example.ws.application.dto.ClientRequestDto;
import com.example.ws.domain.model.ClientDomain;
import com.example.ws.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateClientUseCase {

    private final ClientRepository clientRepository;

    public UpdateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDomain execute(String clientCode, ClientRequestDto client) {
        return clientRepository.update(clientCode, client);
    }
}
