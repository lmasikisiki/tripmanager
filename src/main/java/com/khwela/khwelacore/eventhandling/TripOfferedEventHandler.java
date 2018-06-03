package com.khwela.khwelacore.eventhandling;

import com.khwela.khwelacore.events.TripOfferedEvent;
import com.khwela.khwelacore.services.OfferService;
import com.khwela.khwelacore.services.RequestService;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class TripOfferedEventHandler {

    private EventBus eventBus;
    private RequestService requestService;
    private OfferService offerService;

    public TripOfferedEventHandler(EventBus eventBus, OfferService offerService, RequestService requestService) {
        this.eventBus = eventBus;
        this.offerService = offerService;
        this.requestService = requestService;
    }

    @EventHandler
    public void on(TripOfferedEvent event) throws Exception {
        System.out.println("attempt assigment");
        this.requestService.attemptAssignment();
    }

}
