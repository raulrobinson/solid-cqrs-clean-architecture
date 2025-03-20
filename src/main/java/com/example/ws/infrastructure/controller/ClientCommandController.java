package com.example.ws.infrastructure.controller;

import com.example.ws.application.dto.ClientDto;
import com.example.ws.application.dto.ClientRequestDto;
import com.example.ws.application.dto.PartialClientDto;
import com.example.ws.application.mapper.ClientMapper;
import com.example.ws.application.usecases.CreateClientUseCase;
import com.example.ws.application.usecases.DeleteClientUseCase;
import com.example.ws.application.usecases.PartialUpdateClientUseCase;
import com.example.ws.application.usecases.UpdateClientUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${controller.properties.base-path}/clients")
@Tag(name = "Client", description = "Client operations")
public class ClientCommandController {

    private final CreateClientUseCase createClient;
    private final UpdateClientUseCase updateClient;
    private final PartialUpdateClientUseCase partialUpdateClient;
    private final DeleteClientUseCase deleteClient;

    public ClientCommandController(CreateClientUseCase createClient, UpdateClientUseCase updateClient, PartialUpdateClientUseCase partialUpdateClient, DeleteClientUseCase deleteClient) {
        this.createClient = createClient;
        this.updateClient = updateClient;
        this.partialUpdateClient = partialUpdateClient;
        this.deleteClient = deleteClient;
    }

    /**
     * Create client
     * @param clientDto Client data (required)
     * @return ClientDto (created)
     */
    @PostMapping()
    @Operation(summary = "Create client", description = "Create client")
    @ApiResponse(responseCode = "201", description = "Client created")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<ClientDto> create(@RequestBody ClientRequestDto clientDto) {
        ClientDto client = ClientMapper.INSTANCE.toClientDto(createClient.execute(clientDto));
        if (client == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    /**
     * Update client
     * @param clientCode Client code (required)
     * @param clientDto Client data (required)
     * @return ClientDto (updated)
     */
    @PutMapping("/{clientCode}")
    @Operation(summary = "Update client", description = "Update client")
    @ApiResponse(responseCode = "200", description = "Client updated")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<ClientDto> update(@PathVariable String clientCode,
                                            @RequestBody ClientRequestDto clientDto) {
        ClientDto client = ClientMapper.INSTANCE.toClientDto(updateClient.execute(clientCode, clientDto));
        if (client == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    /**
     * Partial update client
     * @param clientCode Client code (required)
     * @param partialClientDto Partial client data (required)
     * @return ClientDto (updated)
     */
    @PatchMapping("/status/{clientCode}")
    @Operation(summary = "Partial update client", description = "Partial update client")
    @Parameter(name = "clientCode", description = "Client ID", required = true, example = "1")
    @ApiResponse(responseCode = "200", description = "Client updated")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<ClientDto> partialUpdate(@PathVariable String clientCode,
                                                   @RequestBody PartialClientDto partialClientDto) {
        ClientDto client = ClientMapper.INSTANCE.toClientDto(partialUpdateClient.execute(clientCode, partialClientDto));
        if (client == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    /**
     * Delete client
     * @param clientCode Client code (required)
     */
    @DeleteMapping("/{clientCode}")
    @Operation(summary = "Delete client", description = "Delete client")
    @Parameter(name = "clientCode", description = "Client client Code", required = true, example = "CLI-1712345678903")
    @ApiResponse(responseCode = "204", description = "Client deleted")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public void delete(@PathVariable String clientCode) {
        deleteClient.execute(clientCode);
    }

}
