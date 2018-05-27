package com.khwela.khwelacore.eventhandling;

import com.khwela.khwelacore.enums.TripStatus;
import com.khwela.khwelacore.models.TripRecord;
import com.khwela.khwelacore.models.TripRequest;
import com.khwela.khwelacore.repositories.TripRepository;
import com.khwela.khwelacore.repositories.TripRequestRepository;
import com.khwela.khwelacore.trips.*;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class EventHandlers {

    private TripRepository tripRepository;
    private TripRequestRepository tripRequestRepository;
    private EventBus eventBus;

    public EventHandlers(TripRepository tripRepository, TripRequestRepository tripRequestRepository, EventBus eventBus) {
        this.tripRequestRepository = tripRequestRepository;
        this.tripRepository = tripRepository;
        this.eventBus = eventBus;
    }

    @EventHandler
    public void onE(TripRequestStatusChangedEvent event) {
        tripRequestRepository.UpdateTripRequestStatus(event.getRequestId(), event.getNewStaus());
    }

    @EventHandler
    public void on(TripStatusChangedEvent event) {
        tripRepository.UpdateTripStatus(event.getTripId(), TripStatus.CANCELLED);
    }

    @EventHandler
    public void onE(UserNotifiedOfCancellationEvent event) {
        System.out.println("Good job, User has been notified of the cancellation...");
    }



}