package com.example.ws.infrastructure.persistence;

import com.example.ws.infrastructure.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataClientRepository extends JpaRepository<ClientEntity, Long> {

    ClientEntity findClientByDni(String dni);

    ClientEntity findClientEntityByDniAndClientCode(String dni, String clientCode);

    ClientEntity findClientEntityByClientCode(String clientCode);

}
