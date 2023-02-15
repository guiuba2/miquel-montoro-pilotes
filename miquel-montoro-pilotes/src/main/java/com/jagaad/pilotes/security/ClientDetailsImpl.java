package com.jagaad.pilotes.security;

import com.jagaad.pilotes.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ClientDetailsImpl implements UserDetails {

    private final Client client;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     //   return Collections.emptyList();
       return Arrays.stream(client.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return client.getPassword();//getPhoneNumber();
    }

    @Override
    public String getUsername() {
        return client.getFirstName(); //getPhoneNumber()
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
