package com.element.assignment.repository;

import com.element.assignment.document.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends MongoRepository<Trip, String> {

//    @Query("{ 'tripName' : ?0 }")
//    public Trip findTripDetailsByTripName(String tripName);

    Trip findByTripName(String tripName);

    Trip findByTripId(String tripId);
}
