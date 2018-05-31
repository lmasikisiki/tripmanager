package com.khwela.khwelacore.aggregates;

import com.khwela.khwelacore.commands.RequestTripCommand;
import com.khwela.khwelacore.events.TripRequestedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;

@Aggregate
@SuppressWarnings("unused")
public class Requests implements Serializable {

    @AggregateIdentifier
    private String requestId;

    @CommandHandler
    public Requests(RequestTripCommand command) {
        AggregateLifecycle.apply(new TripRequestedEvent(command.getTripRequestId(),
                command.getUserId(),
                command.getPickup(),
                command.getDestination(),
                command.getTripDate(),
                command.getNumberOfPeople(),
                command.getLuggageSize()
        ));
    }

    @EventSourcingHandler
    public void on(TripRequestedEvent event) {
        this.requestId = event.getTripRequestId();
    }

    public Requests() {
    }
}
