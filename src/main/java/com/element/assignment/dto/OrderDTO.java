package com.element.assignment.dto;

import com.element.assignment.document.Trip;

import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderDTO {

    @NotNull(message = "No Users added.")
    private List<UserDTO> hikers;

    @NotNull(message = "Order value cannot be blank.")
    private Double orderValue;

    @NotNull(message = "No trip is selected.")
    private Trip tripDetails;

    public OrderDTO(List<UserDTO> hikers, Double orderValue, Trip tripDetails) {
        this.hikers = hikers;
        this.orderValue = orderValue;
        this.tripDetails = tripDetails;
    }

    public OrderDTO() {
    }

    public List<UserDTO> getHikers() {
        return hikers;
    }

    public void setHikers(List<UserDTO> hikers) {
        this.hikers = hikers;
    }

    public Double getOrderValue() {
        return orderValue;
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
}