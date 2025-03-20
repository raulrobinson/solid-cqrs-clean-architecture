package com.example.ws.infrastructure.persistence;

import com.example.ws.application.dto.ClientRequestDto;
import com.example.ws.application.dto.PartialClientDto;
import com.example.ws.application.mapper.ClientMapper;
import com.example.ws.domain.model.ClientDomain;
import com.example.ws.domain.repository.ClientRepository;
import com.example.ws.infrastructure.persistence.entity.ClientEntity;
import com.example.ws.infrastructure.persistence.enumeration.ClientType;
import com.example.ws.infrastructure.persistence.enumeration.DniType;
import com.example.ws.infrastructure.shared.exception.GlobalException;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaClientRepository implements ClientRepository {

	private static final Logger transactionLogger = org.slf4j.LoggerFactory.getLogger("TRANSACTION_AUDIT");	

	private final SpringDataClientRepository springDataClientRepository;
	private final ClientMapper clientMapper;

	public JpaClientRepository(SpringDataClientRepository springDataClientRepository, ClientMapper clientMapper) {
		this.springDataClientRepository = springDataClientRepository;
		this.clientMapper = clientMapper;
	}

	// Get all clients
	@Override
	public List<ClientDomain> getAll() {
		return springDataClientRepository.findAll().stream()
			.map(clientMapper::toClientDomain)
			.toList();
	}

	// Get clients by Client Code
	@Override
	public ClientDomain getByClientCode(String ClientCode) {
		ClientEntity clientEntity = springDataClientRepository.findClientEntityByClientCode(ClientCode);
		if (clientEntity == null) throw new GlobalException("Client with CLIENT CODE: " + ClientCode + " not found");
		return clientMapper.toClientDomain(clientEntity);
	}

	// Get clients by DNI
	@Override
	public ClientDomain getClientByDni(String dni) {
		ClientEntity clientEntity = springDataClientRepository.findClientByDni(dni);
		if (clientEntity == null) throw new GlobalException("Client with DNI: " + dni + " not found");
		return clientMapper.toClientDomain(clientEntity);
	}

	// Create client
	@Override
	public ClientDomain create(ClientRequestDto client) {
        ClientEntity findClientEntityByDni = springDataClientRepository.findClientByDni(client.getDni());
        if (findClientEntityByDni != null) throw new GlobalException("Client already exists with DNI " + client.getDni());
		ClientEntity savedClientEntity = springDataClientRepository.save(clientMapper.toClientEntity(client));
		if (savedClientEntity.getId() != null) {
			transactionLogger.info("Client with CLIENT CODE: {} and ID {} and DNI {} saved", savedClientEntity.getClientCode(), savedClientEntity.getId(), savedClientEntity.getDni());
			return clientMapper.toClientDomain(savedClientEntity);
		}
		throw new GlobalException("Client not saved");
	}

	// Update client
	@Override
	public ClientDomain update(String clientCode,
							   ClientRequestDto clientDto) {
		// Check if client Exists by ID and DNI match
		ClientEntity clientByDniAndId = springDataClientRepository.findClientEntityByDniAndClientCode(clientDto.getDni(), clientCode);
		if (clientByDniAndId == null) {
			throw new GlobalException("Client with DNI: " + clientDto.getDni() + " and CLIENT CODE: " + clientCode + " not match or not found");
		} else {

			// Update Client
			clientByDniAndId.setName(clientDto.getName());
			clientByDniAndId.setDni(String.valueOf(DniType.valueOf(clientDto.getDniType())));
			clientByDniAndId.setGender(clientDto.getGender());
			clientByDniAndId.setAge(clientDto.getAge());
			clientByDniAndId.setPhone(clientDto.getPhone());
			clientByDniAndId.setAddress(clientDto.getAddress());
			clientByDniAndId.setPassword(clientDto.getPassword());
			clientByDniAndId.setActive(clientDto.isActive());
			clientByDniAndId.setClientType(String.valueOf(ClientType.valueOf(clientDto.getClientType())));
			clientByDniAndId.setClientCode(clientCode);

			// Save updated Client
			ClientEntity updatedClient = springDataClientRepository.save(clientByDniAndId);
			if (updatedClient.getId() != null) {
				transactionLogger.info("Client with CLIENT CODE: {} updated", updatedClient.getClientCode());
				return clientMapper.toClientDomain(updatedClient);
			}

			throw new GlobalException("Client not updated");
		}
	}

    // Partial update account
	@Override
    public ClientDomain partialUpdate(String clientCode,
									  PartialClientDto partialClientDto) {
		// Check if exists client by ID
		ClientEntity clientById = springDataClientRepository.findClientEntityByClientCode(clientCode);
        if (clientById == null) throw new GlobalException("Client with CLIENT CODE: " + clientCode + " not found");

		// Update Client
		clientById.setActive(partialClientDto.isActive());

		// Save updated Client
		ClientEntity updatedClient = springDataClientRepository.save(clientById);
		if (updatedClient.getId() != null) {
			transactionLogger.info("Client status with CLIENT CODE: {} updated", updatedClient.getClientCode());
			return clientMapper.toClientDomain(updatedClient);
		}

		throw new GlobalException("Client status not updated");
    }

	// Delete client
	@Override
	public void deleteByClientCode(String clientCode) {
		if (clientCode == null) throw new GlobalException("Client code is null");
		ClientEntity clientByClientCode = springDataClientRepository.findClientEntityByClientCode(clientCode);
		if (clientByClientCode == null) throw new GlobalException("Client with CLIENT CODE: " + clientCode + " not found");
		transactionLogger.info("Client with CLIENT CODE: {} deleted", clientByClientCode.getClientCode());
		springDataClientRepository.delete(clientByClientCode);
	}

}
