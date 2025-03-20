package com.example.ws.domain.repository;

import com.example.ws.application.dto.ClientRequestDto;
import com.example.ws.application.dto.PartialClientDto;
import com.example.ws.domain.model.ClientDomain;

import java.util.List;

public interface ClientRepository {

    List<ClientDomain> getAll();
    ClientDomain getByClientCode(String clientCode);

    // Get clients by DNI
    ClientDomain getClientByDni(String dni);

    ClientDomain create(ClientRequestDto client);
    ClientDomain update(String clientCode, ClientRequestDto clientDto);
    ClientDomain partialUpdate(String clientCode, PartialClientDto client);
    void deleteByClientCode(String clientCode);
}
