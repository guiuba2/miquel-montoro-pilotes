package com.jagaad.pilotes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
  //  @JsonIgnore
    private long orderId;

    @ManyToOne
    @JoinColumn(name = "phone_number")
    @JsonIgnore
    @CreatedBy
    private Client client;

    @NotBlank
    @NonNull
    @Column(name = "deliveryAddress")
    private String deliveryAddress;


    @Column(name = "number_of_pilotes")
    private int numberOfPilotes;

//    @JsonIgnore
    private LocalDateTime date;

    @Column(name = "order_total")
  //  @JsonIgnore
    private String orderTotal;



}
