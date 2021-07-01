package com.element.assignment;

import com.element.assignment.config.DataNotFoundException;
import com.element.assignment.document.Order;
import com.element.assignment.document.Trip;
import com.element.assignment.dto.OrderDTO;
import com.element.assignment.dto.UserDTO;
import com.element.assignment.repository.OrderRepository;
import com.element.assignment.repository.TripRepository;
import com.element.assignment.service.OrderService;
import com.element.assignment.service.OrderServiceImpl;
import com.element.assignment.service.TripService;
import com.element.assignment.service.TripServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {

	@MockBean
	private OrderRepository orderRepository;

	@Autowired
	private OrderService orderService = new OrderServiceImpl();

	@MockBean
	private TripRepository tripRepository;

	@Autowired
	private TripService tripService = new TripServiceImpl();

	private Trip tripDetails;

	private UserDTO userDTO;

	private Order order;

	@BeforeEach
	void init(){
		tripDetails = new Trip("Shire", new Date(), new Date(), 5, 100, 29.90);
		tripDetails.setTripId("TestTripId");
		userDTO = new UserDTO("TestUser", 18, "9711043499","test@email.com");
		order = new Order(Arrays.asList(userDTO), "test@email.com", 29.90, tripDetails, false);
	}

	@Test
	@DisplayName("Give Order's Trip value mismatches that of the one within the database.")
	void saveOrder() {
		assertThrows(DataNotFoundException.class,() ->orderService.saveOrder(new OrderDTO(Arrays.asList(userDTO), 29.91, tripDetails)));
	}

	@Test
	@DisplayName("Fetched Order details successfully")
	void getOrderDetails() {
		Mockito.when(orderService.getOrderDetails("TestOrderId")).thenReturn(order);
		assertEquals(order, orderService.getOrderDetails("TestOrderId"));
	}

	@Test
	@DisplayName("Fetched Order Details from give email.")
	void findAllOrdersByEmail() {
		Mockito.when(orderService.findAllOrdersByEmail("test@email.com")).thenReturn(Arrays.asList(order));
		assertEquals(Arrays.asList(order), orderService.findAllOrdersByEmail("test@email.com"));
	}

	@Test
	@DisplayName("Fetched all trips.")
	void findAllTrips(){
		Mockito.when(tripService.findAllTrips()).thenReturn(Arrays.asList(tripDetails));
		assertEquals(Arrays.asList(tripDetails), tripService.findAllTrips());
	}

	@Test
	@DisplayName("Fetched TripDetails from tripId")
	void getTripByTripId(){
		Mockito.when(tripService.getTripByTripId(ArgumentMatchers.anyString())).thenReturn(tripDetails);
		assertEquals(tripDetails, tripService.getTripByTripId("TestTripId"));
	}

}
