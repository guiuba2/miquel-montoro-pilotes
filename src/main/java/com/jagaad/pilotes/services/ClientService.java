package com.jagaad.pilotes.services;

import com.jagaad.pilotes.dao.ClientRepo;
import com.jagaad.pilotes.model.Client;
import com.jagaad.pilotes.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
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

    @Transactional
    public ResponseEntity<String> register(Client client) {
        Optional<Client> clientToAdd = clientRepo.findClientByPhoneNumber(client.getPhoneNumber());
        if (clientToAdd.isPresent())
            return ResponseEntity.badRequest().body("Client already exists.");
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        client.setRole("USER");
        clientRepo.save(client);
        return ResponseEntity.ok().body("Client registered successfully");
    }


    public Optional<List<Client>> findClient(String name) {
        Optional<List<Client>> client = clientRepo.findClientByFirstNameContainingIgnoreCase(name);
        if (client.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No clients found.");
        return client;
    }

    public ResponseEntity<String> deleteClientByPhoneNumber(String phoneNumber) {
        Optional<Client> client = clientRepo.findClientByPhoneNumber(phoneNumber);
        if (client.isPresent()) {
            clientRepo.deleteById(client.get().getPhoneNumber());//delete(client.get());
            return ResponseEntity.ok().body("Client deleted successfully");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found.");
    }
}
