package com.jagaad.pilotes.controllers;

import com.jagaad.pilotes.model.Client;
import com.jagaad.pilotes.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void register(@Valid @RequestBody Client client) {

        clientService.register(client);
    }


}
