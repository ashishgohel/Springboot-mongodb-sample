package com.element.assignment.service;

import com.element.assignment.config.DataNotFoundException;
import com.element.assignment.document.Order;
import com.element.assignment.document.Trip;
import com.element.assignment.dto.OrderDTO;
import com.element.assignment.dto.UserDTO;
import com.element.assignment.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TripService tripService;

    @Override
    public String saveOrder(OrderDTO orderDTO){
        //::TODO:: Do validation on ages, total value is correct,
        Order order = parseRequestObject(orderDTO);

        if(order == null)
            throw new DataNotFoundException("Hikers information is not provided");
        Trip tripDetails = order.getTripDetails();
        if(!validateTripDetails(tripDetails))
            throw new DataNotFoundException("Invalid Trip details.");
        //Calculating the total cost of it and comparing it to the orderValue
        if(!validateTripCost(tripDetails.getUnitPrice(), order.getUsers().size(), order.getOrderValue()))
            throw new DataNotFoundException("Total Cost is incorrect.");

        List<UserDTO> users = order.getUsers();
        if(validateUserDetails(users,tripDetails))
            throw new DataNotFoundException("User's age should be within the range mentioned in the Trip details");
        //Validating the users info and checking if users fall into specified criteria mentioned in the Trip details
        return orderRepository.save(order).getOrderId();
    }

    @Override
    public Order getOrderDetails(String orderId){
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    public List<Order> findAllOrdersByEmail(String email){
        //::TODO:: pagination
        return orderRepository.findByUserEmailAddress(email);
    }

    @Override
    public Boolean cancelOrder(String orderId) throws DataNotFoundException{
        Order order = orderRepository.findByOrderId(orderId);
        if(order == null){
            logger.info("No Such Order available against the orderId={}",orderId);
            throw new DataNotFoundException("Invalid orderId.");
        }
        order.setCancelled(true);
        orderRepository.save(order);
        return true;
    }

    private Order parseRequestObject(OrderDTO orderDTO){
        if(orderDTO.getHikers().size() < 1)
            return null;
        Order order = new Order();
        order.setOrderValue(orderDTO.getOrderValue());
        order.setTripDetails(orderDTO.getTripDetails());
        order.setUsers(orderDTO.getHikers());
        order.setUserEmailAddress(orderDTO.getHikers().get(0).getEmail());
        return order;
    }

    private boolean validateUserDetails(List<UserDTO> users, Trip tripDetails){
        boolean isUserAgeInvalid = false;
        Optional<UserDTO> userDTO = users.parallelStream().filter(user -> user.getAge() > tripDetails.getMaxAge() || user.getAge() < tripDetails.getMinAge()).findFirst();
        isUserAgeInvalid = userDTO.isPresent();
        return isUserAgeInvalid;
    }

    private boolean validateTripDetails(Trip tripDetails){
        boolean isValid = false;
        try{
            logger.info("Trip details = {}", tripDetails.getTripId());
            Trip origTripObj = tripService.getTripByTripId(tripDetails.getTripId());
            if(origTripObj != null){
                isValid = origTripObj.getMaxAge().equals(tripDetails.getMaxAge())
                        && origTripObj.getMinAge().equals(tripDetails.getMinAge())
                        && origTripObj.getUnitPrice().equals(tripDetails.getUnitPrice())
                        && origTripObj.getTripName().equals(tripDetails.getTripName());
            }else{
                logger.info("----------- Entity object is null ----------");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        if(!isValid)
            logger.info("Request is inconsistent with the Original records. Request Trip Details={}",tripDetails);
        return isValid;
    }

    private boolean validateTripCost(Double unitPrice, Integer numberOfUsers, Double totalCost){
        boolean status = totalCost.equals(unitPrice*numberOfUsers);
        logger.info("totalCost = {}, number of Users = {} , unitprice is = {} , validation = {}",totalCost,numberOfUsers,unitPrice );
        return status;
    }

}
