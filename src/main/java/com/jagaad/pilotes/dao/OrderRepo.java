package com.jagaad.pilotes.dao;

import com.jagaad.pilotes.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends CrudRepository<Order, Long> {

    @Override
    Optional<Order> findById(Long aLong);

    Optional<Order> findOrderByOrderId(long orderId);

    List<Order> findAllByClient(String email);


}
