package com.jagaad.pilotes.services;

import com.jagaad.pilotes.dao.ClientRepo;
import com.jagaad.pilotes.model.Client;
import com.jagaad.pilotes.security.ClientDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsServiceImpl implements UserDetailsService {

    private final ClientRepo clientRepo;

    @Autowired
    public ClientDetailsServiceImpl(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        Client client = clientRepo.findClientByFirstName(firstName);
        if (client == null) {
            throw new UsernameNotFoundException("Not found: " + firstName); //
        }
        return new ClientDetailsImpl(client);
    }
}
