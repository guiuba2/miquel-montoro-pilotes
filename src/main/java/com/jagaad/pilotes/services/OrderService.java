package com.jagaad.pilotes.services;

import com.jagaad.pilotes.dao.ClientRepo;
import com.jagaad.pilotes.dao.OrderRepo;
import com.jagaad.pilotes.model.Client;
import com.jagaad.pilotes.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final ClientRepo clientRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo,  ClientRepo clientRepo) {
        this.orderRepo = orderRepo;
        this.clientRepo = clientRepo;
    }

    public ResponseEntity<Order> placeOrder(String phoneNumber, String deliveryAddress, int numberOfPilotes) { // Order orderToSave, UserDetails currentClient
        Optional<Client> client = clientRepo.findClientByPhoneNumber(phoneNumber);
        if (client.isPresent()) {
            if (orderIsOk(numberOfPilotes)) {

                Order newOrder = new Order();
                newOrder.setClient(client.get());
                newOrder.setNumberOfPilotes(numberOfPilotes);
                newOrder.setDeliveryAddress(deliveryAddress);
                setDateAndTotal(newOrder);
                orderRepo.save(newOrder);
                return ResponseEntity.ok().body(newOrder);

            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, orderRefused());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found.");
    }

    public ResponseEntity<Order> updateOrder(long orderId, String deliveryAddress, int numberOfPilotes ) { //, UserDetails currentClient
        Optional<Order> order = orderRepo.findOrderByOrderId(orderId);
        if (order.isPresent()) {
            if (ChronoUnit.MINUTES.between(order.get().getDate(), LocalDateTime.now()) <= 5.0) {
                if (orderIsOk(numberOfPilotes)) {
                    order.get().setDeliveryAddress(deliveryAddress);
                    order.get().setNumberOfPilotes(numberOfPilotes);
                    setDateAndTotal(order.get());
                    orderRepo.save(order.get());
                    return ResponseEntity.ok().body(order.get());
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update not possible, " +
                            orderRefused());

                }

            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update not possible, " +
                        "It's been more than 5 minutes order was placed.");
            }

        }
        throw new

                ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found.");
    }



    public Order setDateAndTotal(Order orderToSave) {
        orderToSave.setDate(LocalDateTime.now());
        orderToSave.setOrderTotal(String.format("%.2fEUR", (orderToSave.getNumberOfPilotes() * 1.33)));
        return orderToSave;
    }

    public boolean orderIsOk(int numberOfPilotes) { //Order orderToSave
        if (numberOfPilotes == 5 || numberOfPilotes == 10 || numberOfPilotes == 15) {
            return true;
        }
        return false;
    }

    public String orderRefused() {
        return "Try again, please choose between 5, 10 or 15 pilotes";
    }



    private String getLoggedUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
