package com.jagaad.pilotes.dao;

import com.jagaad.pilotes.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends CrudRepository<Client, Long> {

    @Query("SELECT s FROM Client s WHERE s.firstName = ?1")
    Client findClientByFirstName(String firstName);

    @Query("SELECT s FROM Client s WHERE s.phoneNumber = ?1")
    Client findClientByPhoneNumber(String phoneNumber);  // Optional<Client>
}
