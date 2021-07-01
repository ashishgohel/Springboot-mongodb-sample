package com.element.assignment.Controller;

import com.element.assignment.config.DataNotFoundException;
import com.element.assignment.document.Order;
import com.element.assignment.document.Trip;
import com.element.assignment.dto.OrderDTO;
import com.element.assignment.service.OrderService;
import com.element.assignment.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/rest/")
@Validated
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private TripService tripService;

    @Autowired
    private OrderService orderService;

    /**
     * 1. View all the trails available for hiking
     * @return
     */
    @GetMapping(value = "/trip/all", produces = "application/json")
    public ResponseEntity<List<Trip>> getAllTrips(){
        return ResponseEntity.ok(tripService.findAllTrips());
    }

    /**
     * 2. View a selected trail
     * @param tripId
     * @return
     */
    @GetMapping(value = "/trip", produces = "application/json")
    public ResponseEntity<Trip> getTripDetailsById(@NotEmpty(message = "Trip Id cannot be blank") @RequestParam("tripId") String tripId){
        return ResponseEntity.ok(tripService.getTripByTripId(tripId));
    }

    /**
     * 3. Book a selected trail for hiking
     * @param order
     * @return
     * @throws DataNotFoundException
     */
    @PostMapping(value = "/order/book", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> saveOrder(@Valid @RequestBody OrderDTO order) throws DataNotFoundException{
        return ResponseEntity.ok(orderService.saveOrder(order));
    }

    /**
     * 4. View a booking
     * @param orderId
     * @return
     */
    @GetMapping(value = "/order", produces = "application/json")
    public ResponseEntity<Order> getOrderDetails(@NotEmpty(message = "Invalid request. Please provide valid orderId.") @RequestParam("orderId") String orderId){
        return ResponseEntity.ok(orderService.getOrderDetails(orderId));
    }

    /**
     * 5. Cancel a booking
     * @param orderId
     * @return
     * @throws DataNotFoundException
     */
    @DeleteMapping(value = "/order/cancel", produces = "application/json")
    public ResponseEntity<Boolean> cancelOrder(@NotEmpty(message = "Invalid request. Please provide valid orderId.") @RequestParam("orderId") String orderId) throws DataNotFoundException{
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }

    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<List<Order>> getAllOrdersByEmail(@NotEmpty(message = "Invalid request. Please provide valid email.") @RequestParam("userEmail") String userEmail){
        return ResponseEntity.ok(orderService.findAllOrdersByEmail(userEmail));
    }

}
