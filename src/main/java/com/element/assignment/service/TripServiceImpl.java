package com.element.assignment.service;

import com.element.assignment.document.Trip;
import com.element.assignment.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService{

    @Autowired
    private TripRepository tripRepository;

    /**
     * As there is no use case for saving new trip, cacheEvict is not getting invoked from anywhere.
     */
    @Override
    @CacheEvict(cacheNames = "TripDetails", allEntries = true)
    public void cacheEvict(){}

    @Override
    @Cacheable(cacheNames = "TripDetails")
    public List<Trip> findAllTrips(){
        return tripRepository.findAll();
    }

    /*@Override
    public Trip getTripByTripId(String tripId){
        Optional<Trip> trip = tripRepository.findById(tripId);
        return trip.isPresent()?trip.get():null;
    }*/

    @Override
    public Trip getTripByTripId(String tripId){
      Trip trip = tripRepository.findByTripId(tripId);
        return trip != null ? trip:null;
    }

}
