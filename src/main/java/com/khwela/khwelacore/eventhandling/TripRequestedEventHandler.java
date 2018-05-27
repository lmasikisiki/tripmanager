package com.khwela.khwelacore.eventhandling;

import com.khwela.khwelacore.enums.TripStatus;
import com.khwela.khwelacore.models.TripRecord;
import com.khwela.khwelacore.models.TripRequest;
import com.khwela.khwelacore.repositories.TripRepository;
import com.khwela.khwelacore.repositories.TripRequestRepository;
import com.khwela.khwelacore.trips.TripRequestedEvent;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.khwela.khwelacore.commons.CommonUtilities.addUserToTripRecord;
import static com.khwela.khwelacore.commons.CommonUtilities.travelOnSameDay;

@Component
public class TripRequestedEventHandler {

    private TripRepository tripRepository;
    private TripRequestRepository tripRequestRepository;
    private EventBus eventBus;

    public TripRequestedEventHandler(TripRepository tripRepository, TripRequestRepository tripRequestRepository, EventBus eventBus) {
        this.tripRequestRepository = tripRequestRepository;
        this.tripRepository = tripRepository;
        this.eventBus = eventBus;
    }

    @EventHandler
    public void on(TripRequestedEvent event) {
        Optional<TripRecord> optionalTrip = tripRepository.findAll().stream()
                .filter(trip ->
                        trip.getStatus() == TripStatus.AVAILABLE &&
                                travelOnSameDay(event.getTripDate(), trip.getTripDate()) &&
                                trip.getUsers().size() < trip.getNumberOfSeats())
                .findFirst();

        if (optionalTrip.isPresent()) {
            TripRecord trip = optionalTrip.get();
            trip = addUserToTripRecord(event.getUserId(), trip);
            tripRepository.save(trip);
        } else {
            TripRequest tripRequest = new TripRequest(event.getUserId(), event.getFrom(), event.getDestination(), event.getTripDate());
            tripRequestRepository.save(tripRequest);
            System.out.println("No trips available for now, we will notify you when we have one");
        }
    }

}
