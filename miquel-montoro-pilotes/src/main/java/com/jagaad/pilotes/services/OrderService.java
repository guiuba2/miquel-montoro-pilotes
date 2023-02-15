package com.jagaad.pilotes.services;

import com.jagaad.pilotes.dao.OrderRepo;
import com.jagaad.pilotes.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepo orderRepo;


    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

   public Order placeOrder(Order orderToSave) {

        return orderRepo.save(orderToSave);
    }

}
