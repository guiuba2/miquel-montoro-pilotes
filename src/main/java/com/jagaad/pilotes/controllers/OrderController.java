package com.jagaad.pilotes.controllers;

import com.jagaad.pilotes.dao.ClientRepo;
import com.jagaad.pilotes.dao.OrderRepo;
import com.jagaad.pilotes.model.Client;
import com.jagaad.pilotes.model.Order;
import com.jagaad.pilotes.services.ClientService;
import com.jagaad.pilotes.services.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(tags = "Place and update orders.")
public class OrderController {
    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;

    }
    @ApiOperation(value = "To place an order please choose between 5, 10 or 15 pilotes. Also inform  client phone number and delivery address.")
    @PostMapping(path = "/placeOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> placeOrder(@Validated
                                            @RequestParam String phoneNumber,
                                            @RequestParam String deliveryAddress,
                                            @RequestParam int numberOfPilotes) {
        return orderService.placeOrder(phoneNumber, deliveryAddress, numberOfPilotes);
    }

    @ApiOperation(value = "Update order using order id")
    @PutMapping(path = "/updateOrder/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> updateOrder(@Validated
                                             @PathVariable long orderId,
                                             @RequestParam String deliveryAddress,
                                             @RequestParam int numberOfPilotes
    ) {
        return orderService.updateOrder(orderId, deliveryAddress, numberOfPilotes);
    }

}
