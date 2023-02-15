package com.jagaad.pilotes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long orderId;

    @ManyToOne
    @JoinColumn(name = "phone_number") //"client_id"
    @JsonIgnore
    private Client client;

    @NotBlank
    @NonNull
    @Column(name = "deliveryAddress")
    private String deliveryAddress;


    @Column(name = "number_of_pilotes")
    private int numberOfPilotes;

    private LocalDateTime date;

    @Column(name = "order_total")
    @JsonIgnore
    private String orderTotal;



 /*   @JsonIgnore
    private String phoneNumber;*/
}
