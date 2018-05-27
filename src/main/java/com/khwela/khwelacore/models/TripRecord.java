package com.khwela.khwelacore.models;

import com.khwela.khwelacore.enums.TripStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class TripRecord {

    @Id
    private String id;
    private String offeredBy;
    private String pickup;
    private String destination;
    private TripStatus status;
    private ArrayList users = new ArrayList();
    private int numberOfSeats;
    private Date tripDate;
    public TripRecord(String tripId, String offeredBy, String pickup, String destination,Date tripDate) {
        this.id= tripId;
        this.offeredBy=offeredBy;
        this.pickup=pickup;
        this.destination= destination;
        this.status= TripStatus.AVAILABLE;
        this.tripDate=tripDate;
    }

    public TripRecord(String tripId,String offeredBy, String pickup, String destination, int numberOfSeats, Date tripDate) {
        this.id= tripId;
        this.offeredBy = offeredBy;
        this.pickup = pickup;
        this.destination = destination;
        this.status= TripStatus.AVAILABLE;
        this.numberOfSeats = numberOfSeats;
        this.tripDate = tripDate;
    }

    @Override
    public String toString() {
        return "TripRecord{" +
                "id='" + id + '\'' +
                ", offeredBy='" + offeredBy + '\'' +
                ", pickup='" + pickup + '\'' +
                ", destination='" + destination + '\'' +
                ", status=" + status +
                ", users=" + users +
                ", numberOfSeats=" + numberOfSeats +
                ", tripDate=" + tripDate +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(String offeredBy) {
        this.offeredBy = offeredBy;
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

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public ArrayList getUsers() {
        return users;
    }

    public void setUsers(ArrayList users) {
        this.users = users;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public TripRecord(){}
}
