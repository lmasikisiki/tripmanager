package com.khwela.khwelacore.models;

import com.khwela.khwelacore.enums.TripRequestStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class TripRequest implements Serializable {

    @Id
     private String id;
    private String userId;
    private  String pickup;
    private String destination;
    private LocalDate tripDate;
    private TripRequestStatus status;
    private int numberOfPeople;
    public TripRequest(String userId, String pickup, String destination, LocalDate tripDate,int numberOfPeople) {
        this.userId = userId;
        this.pickup = pickup;
        this.destination = destination;
        this.tripDate = tripDate;
        this.status = status;
        this.status= TripRequestStatus.OPEN;
        this.numberOfPeople= numberOfPeople;
    }

    public TripRequest(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public void setTripDate(LocalDate tripDate) {
        this.tripDate = tripDate;
    }

    public TripRequestStatus getStatus() {
        return status;
    }

    public void setStatus(TripRequestStatus status) {
        this.status = status;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}
