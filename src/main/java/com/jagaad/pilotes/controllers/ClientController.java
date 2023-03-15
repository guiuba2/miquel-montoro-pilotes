package com.jagaad.pilotes.controllers;

import com.jagaad.pilotes.model.Client;
import com.jagaad.pilotes.model.ClientDTO;
import com.jagaad.pilotes.model.ClientMapper;
import com.jagaad.pilotes.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(tags = "Register, delete or find clients and their orders.")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }


    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Register a client. Please ignore order fields in this step")
    public ResponseEntity<String> register(@Valid @RequestBody Client client) {

        return clientService.register(client);
    }


    @GetMapping("/findClient/{name}")
    @Validated
    @ApiOperation("Find clients by name. Partial searches allowed: e.g. all orders of clients whose  name contains an “a” in their name.")
    public ResponseEntity<List<ClientDTO>> findClient(HttpServletResponse response,
                                                      @Validated
                                                      @PathVariable("name") @Size(min = 1) String name
                                                      ) {
        Optional<List<Client>> clients = clientService.findClient(name);
            List<ClientDTO> clientsDTO = clientMapper.toClientDTOList(clients.get());
            return ResponseEntity.ok(clientsDTO);
    }

    @DeleteMapping(value = "/deleteClient/{phoneNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Find and delete client by phone number.")
    public ResponseEntity<String> deleteClient(
            @Validated
            @PathVariable("phoneNumber") @Size(min = 8) String phoneNumber
    ) {
        return clientService.deleteClientByPhoneNumber(phoneNumber);
    }


}
