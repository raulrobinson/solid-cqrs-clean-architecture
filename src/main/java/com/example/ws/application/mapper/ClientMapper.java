package com.example.ws.application.mapper;

import com.example.ws.application.dto.ClientDto;
import com.example.ws.application.dto.ClientRequestDto;
import com.example.ws.domain.model.ClientDomain;
import com.example.ws.infrastructure.persistence.entity.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClientMapper {

    ClientMapper INSTANCE = new ClientMapperImpl();

    ClientDomain toClientDomain(ClientEntity clientEntity);
    ClientEntity toClientEntity(ClientRequestDto clientRequestDto);
    ClientDto toClientDto(ClientDomain clientDomain);
    List<ClientDto> toClientDtoList(List<ClientDomain> clientDomainList);
}
