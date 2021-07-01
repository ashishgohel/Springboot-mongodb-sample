package com.element.assignment.repository;

import com.element.assignment.document.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Order findByOrderId(String orderId);

    List<Order> findByUserEmailAddress(String email);
}
