package com.khwela.khwelacore;

import com.khwela.khwelacore.enums.TripStatus;
import com.khwela.khwelacore.models.TripRecord;
import com.khwela.khwelacore.repositories.TripRepository;
import com.khwela.khwelacore.trips.TripCancelledCommand;
import com.khwela.khwelacore.trips.TripOfferedEvent;
import com.khwela.khwelacore.trips.TripStatusChangedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Component
public class EventHandling {

    private TripRepository tripRepository;

    public EventHandling( TripRepository tripRepository){
        this.tripRepository= tripRepository;
    }

    @EventHandler
    public void on(TripOfferedEvent event){
        System.out.println("Event handler called..");
        tripRepository.save(new TripRecord(event.getTripId(), event.getOfferedBy(),event.getFrom(),event.getDestination()));
    }

    @EventHandler
    public void on(TripCancelledCommand event){
        System.out.println("Event handler called..");
        apply(new TripStatusChangedEvent(event.getTripId(), TripStatus.AVAILABLE, TripStatus.CANCELLED));

    }

}
