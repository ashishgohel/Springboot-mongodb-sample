package com.element.assignment.document;


import com.element.assignment.dto.UserDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Document
public class Order {

    @Id
    private String orderId;

    @NotNull
    private List<UserDTO> users;

    @Indexed(unique = false)
    @Email
    @NotEmpty
    private String userEmailAddress;

    @NotNull
    private Double orderValue;

    @NotNull
    private Trip tripDetails; //Storing complete Trip object instead of storing ID or creating separate TripDTO as Trip document may update its value. Hence storing the data within the Order document at the time of booking so that the changes in the Trip document would not affect.

    private boolean cancelled;

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public void setOrderValue(Double orderValue) {
        this.orderValue = orderValue;
    }

    public Trip getTripDetails() {
        return tripDetails;
    }

    public void setTripDetails(Trip tripDetails) {
        this.tripDetails = tripDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public Double getOrderValue() {
        return orderValue;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    public Order() {
    }

    public Order(@NotNull List<UserDTO> users, @Email @NotEmpty String userEmailAddress, @NotNull Double orderValue, Trip tripDetails, boolean cancelled) {
        this.users = users;
        this.userEmailAddress = userEmailAddress;
        this.orderValue = orderValue;
        this.tripDetails = tripDetails;
        this.cancelled = cancelled;
    }
}
