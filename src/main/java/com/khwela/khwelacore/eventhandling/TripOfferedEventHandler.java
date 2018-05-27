package com.khwela.khwelacore.eventhandling;

import com.khwela.khwelacore.enums.TripRequestStatus;
import com.khwela.khwelacore.models.TripRecord;
import com.khwela.khwelacore.models.TripRequest;
import com.khwela.khwelacore.repositories.TripRepository;
import com.khwela.khwelacore.repositories.TripRequestRepository;
import com.khwela.khwelacore.trips.TripOfferedEvent;
import com.khwela.khwelacore.trips.TripRequestStatusChangedEvent;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.GenericEventMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.khwela.khwelacore.commons.CommonUtilities.*;

@Component
public class TripOfferedEventHandler {

    private TripRepository tripRepository;
    private TripRequestRepository tripRequestRepository;
    private EventBus eventBus;

    public TripOfferedEventHandler(TripRepository tripRepository, TripRequestRepository tripRequestRepository, EventBus eventBus) {
        this.tripRequestRepository = tripRequestRepository;
        this.tripRepository = tripRepository;
        this.eventBus = eventBus;
    }

    @EventHandler
    public void on(TripOfferedEvent event) {
        TripRecord tripRecord = new TripRecord(event.getTripId(),event.getOfferedBy(),event.getFrom(),
                event.getDestination(),event.getNumberOfSeats(),event.getTripDate());

        List<TripRequest> optinalRequest = tripRequestRepository.findAll()
                .stream()
                .filter(t -> t.getStatus() == TripRequestStatus.OPEN &&
                        travelOnSameDay(t.getTripDate(),tripRecord.getTripDate()))
                .limit(tripRecord.getNumberOfSeats()).collect(Collectors.toList());

        optinalRequest.forEach(request -> {
            int requestId = request.getId();
            String userId = request.getUserId();
            addUserToTripRecord(userId, tripRecord);
            eventBus.publish(GenericEventMessage
                    .asEventMessage(new TripRequestStatusChangedEvent(event.getId(),requestId, TripRequestStatus.ASSIGNED)));
        });

        tripRepository.save(tripRecord);
    }

}
