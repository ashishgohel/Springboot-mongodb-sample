package com.element.assignment.service;

import com.element.assignment.document.Trip;

import java.util.List;

public interface TripService {


    void cacheEvict();

    List<Trip> findAllTrips();

    Trip getTripByTripId(String tripName);
}
