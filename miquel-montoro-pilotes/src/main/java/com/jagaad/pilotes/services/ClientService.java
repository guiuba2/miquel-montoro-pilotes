package com.jagaad.pilotes.services;

import com.jagaad.pilotes.dao.ClientRepo;
import com.jagaad.pilotes.model.Client;
import com.jagaad.pilotes.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepo clientRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientService(ClientRepo clientRepo, PasswordEncoder passwordEncoder) {
        this.clientRepo = clientRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(Client client) {
        Optional<Client> clientToAdd = Optional.ofNullable(clientRepo.findClientByPhoneNumber(client.getPhoneNumber()));
        if (clientToAdd.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client already exists.");
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        clientRepo.save(client);
    }


}
