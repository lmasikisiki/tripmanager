package com.khwela.khwelacore.models;

import com.khwela.khwelacore.enums.TripStatus;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TripRecord {

    @Id
    private String id;
    private String offeredBy;
    private String pickup;
    private String destination;
    private TripStatus status;

    public TripRecord(String tripId, String offeredBy, String pickup, String destination) {
        this.id= tripId;
        this.offeredBy=offeredBy;
        this.pickup=pickup;
        this.destination= destination;
        this.status= TripStatus.AVAILABLE;
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

    public TripRecord(){}
}
