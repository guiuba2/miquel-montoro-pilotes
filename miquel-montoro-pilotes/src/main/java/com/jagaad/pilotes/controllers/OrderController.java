package com.jagaad.pilotes.controllers;

import com.jagaad.pilotes.dao.ClientRepo;
import com.jagaad.pilotes.dao.OrderRepo;
import com.jagaad.pilotes.model.Order;
import com.jagaad.pilotes.services.ClientService;
import com.jagaad.pilotes.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;
    private final ClientService clientService;
    private final OrderRepo orderRepo;
    private final ClientRepo clientRepo;

    @Autowired
    public OrderController(OrderService orderService, ClientService clientService, OrderRepo orderRepo, ClientRepo clientRepo) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.orderRepo = orderRepo;
        this.clientRepo = clientRepo;
    }


    @PostMapping(path = "/placeOrder")
    @ResponseStatus(HttpStatus.OK)
    public String placeOrder(@Valid @RequestBody Order orderToSave,
                             @AuthenticationPrincipal UserDetails currentClient) {

        int pilotes = orderToSave.getNumberOfPilotes();
        DecimalFormat df = new DecimalFormat("0.00");
        if (pilotes == 5 || pilotes == 10 || pilotes == 15) {
            orderToSave.setDate(LocalDateTime.now());
            orderToSave.setOrderTotal(String.format("%s EUR", df.format(pilotes * 1.33) ));
            return "Client: " + currentClient.getUsername() +
                    " Order registered. Please update it within 5 minutes if necessary" +
                    "Order details: " + orderToSave;

        }
        return "Client " + currentClient.getUsername().toString() +
                " ,your order has not been registered. " +
                "Please choose between 5, 10 or 15 pilotes";

    }

}
