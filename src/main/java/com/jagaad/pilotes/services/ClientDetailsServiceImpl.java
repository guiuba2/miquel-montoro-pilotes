package com.jagaad.pilotes.services;

import com.jagaad.pilotes.dao.ClientRepo;
import com.jagaad.pilotes.model.Client;
import com.jagaad.pilotes.security.ClientDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientDetailsServiceImpl implements UserDetailsService {

    private final ClientRepo clientRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Client client = clientRepo.findClientByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new ClientDetailsImpl(client);

    }
}

