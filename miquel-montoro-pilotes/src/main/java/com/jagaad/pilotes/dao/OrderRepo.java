package com.jagaad.pilotes.dao;

import com.jagaad.pilotes.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepo extends CrudRepository<Order, Long> {
   // @Query("select o from Order o where o.client.clientId = ?1")
    List<Order> findOrdersByClient_FirstName(String first_name);
    //findRecipesByNameIgnoreCaseContainsOrderByDateDesc(String name);

    Order findOrderByClientPhoneNumber(String phoneNumber);
    Order findOrderByOrderId(long orderId);


}
