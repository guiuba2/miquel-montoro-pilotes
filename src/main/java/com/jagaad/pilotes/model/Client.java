package com.jagaad.pilotes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    private static final String emailRegex = ".+@.+\\..+";


    @Id
    @NonNull
    @NotBlank
    @Size(min = 8, message = "PASSWORD MUST HAVE AT LEAST 8 CHARACTERS.")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NonNull
    @NotBlank
    @Email(regexp = emailRegex, message = "EMAIL MUST FOLLOW USERNAME@DOMAIN.COM FORMAT")
    private String email;

    @NotBlank
    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @NonNull
    @Column(name = "last_name")
    private String lastName;


    @NonNull
    @NotBlank
    @Size(min = 5, message = "PASSWORD MUST HAVE AT LEAST 5 CHARACTERS.")
    @Column(name = "password")
    private String password;


    @Column(name = "role")
    private String role; // = "USER";


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders;


}
