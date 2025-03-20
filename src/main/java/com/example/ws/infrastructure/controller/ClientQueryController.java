package com.example.ws.infrastructure.controller;

import com.example.ws.application.dto.ClientDto;
import com.example.ws.application.mapper.ClientMapper;
import com.example.ws.application.usecases.FindClientByDniUseCase;
import com.example.ws.application.usecases.FindClientByIdUseCase;
import com.example.ws.application.usecases.GetAllClientsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${controller.properties.base-path}/clients")
@Tag(name = "Client", description = "Client operations")
public class ClientQueryController {

    private final GetAllClientsUseCase findAllClients;
    private final FindClientByIdUseCase findClientByClientCode;
    private final FindClientByDniUseCase findClientByDni;

    public ClientQueryController(GetAllClientsUseCase findAllClients, FindClientByIdUseCase findClientByClientCode, FindClientByDniUseCase findClientByDni) {
        this.findAllClients = findAllClients;
        this.findClientByClientCode = findClientByClientCode;
        this.findClientByDni = findClientByDni;
    }

    /**
     * Get all clients
     * @return List of ClientDto
     */
    @GetMapping
    @Operation(summary = "Get all clients", description = "Get all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of clients"),
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<ClientDto> clients = ClientMapper.INSTANCE.toClientDtoList(findAllClients.handle());
        if (clients.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    /**
     * Get client by client Code
     * @param clientCode (required)
     * @return ClientDto (found)
     */
    @GetMapping("/client-code/{clientCode}")
    @Operation(summary = "Get client by client Code", description = "Get client by client Code")
    @Parameter(name = "clientCode", description = "Client Code", required = true, example = "CLI-1742128362966")
    @ApiResponse(responseCode = "200", description = "Client")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<ClientDto> getByClientCode(@PathVariable String clientCode) {
        ClientDto client = ClientMapper.INSTANCE.toClientDto(findClientByClientCode.handle(clientCode));
        if (client == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/dni/{dni}")
    @Operation(summary = "Get client by DNI", description = "Get client by DNI")
    @Parameter(name = "dni", description = "Client DNI", required = true, example = "12345678")
    @ApiResponse(responseCode = "200", description = "Client")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<ClientDto> getByDni(@PathVariable String dni) {
        ClientDto client = ClientMapper.INSTANCE.toClientDto(findClientByDni.handle(dni));
        if (client == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

}
