package com.element.assignment.service;

import com.element.assignment.document.Order;
import com.element.assignment.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    String saveOrder(OrderDTO order) ;

    Order getOrderDetails(String orderId);

    List<Order> findAllOrdersByEmail(String email);

    Boolean cancelOrder(String orderId);
}
