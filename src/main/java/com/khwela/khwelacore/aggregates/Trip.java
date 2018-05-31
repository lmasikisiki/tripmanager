package com.khwela.khwelacore.aggregates;

import com.khwela.khwelacore.commands.CancelTripCommand;
import com.khwela.khwelacore.commands.OfferTripCommand;
import com.khwela.khwelacore.events.TripCancelledEvent;
import com.khwela.khwelacore.events.TripOfferedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;

import static org.axonframework.commandhandling.model.AggregateLifecycle.*;

@Aggregate
@SuppressWarnings("unused")
public class Trip implements Serializable {

    @AggregateIdentifier
    private String tripIdentifier;

    @CommandHandler
    public Trip(OfferTripCommand command) {
        apply(new TripOfferedEvent(command.getTripId(),
                                    command.getOfferedBy(),
                                    command.getPickup(),
                                    command.getDestination(),
                                    command.getNumberOfSeats(),
                                    command.getTripDate()));
    }

    @CommandHandler
    public  Trip(CancelTripCommand command){
        apply(new TripCancelledEvent(command.getTripId(),
                command.getUserId(),
                command.getCancellationReason()));
    }

    @EventSourcingHandler
    public void on(TripOfferedEvent event){
         this.tripIdentifier = event.getTripId();
    }

    @EventSourcingHandler
    public void on(TripCancelledEvent event){ this.tripIdentifier= event.getTripId(); }


    public Trip() {}
}
