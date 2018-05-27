package com.khwela.khwelacore.models;

import com.khwela.khwelacore.enums.TripRequestStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class TripRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userId;
    private  String pickup;
    private String destination;
    private Date tripDate;
    private TripRequestStatus status;

    public TripRequest(String userId, String pickup, String destination, Date tripDate) {
        this.userId = userId;
        this.pickup = pickup;
        this.destination = destination;
        this.tripDate = tripDate;
        this.status = status;
        this.status= TripRequestStatus.OPEN;
    }

    public TripRequest(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public TripRequestStatus getStatus() {
        return status;
    }

    public void setStatus(TripRequestStatus status) {
        this.status = status;
    }
}
