package com.khwela.khwelacore.eventhandling;

import com.khwela.khwelacore.events.TripOfferedEvent;
import com.khwela.khwelacore.repositories.TripRepository;
import com.khwela.khwelacore.repositories.TripRequestRepository;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

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
 }
