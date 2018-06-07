package com.khwela.khwelacore.aggregates

import com.khwela.khwelacore.commands.DirectRequestCommand
import com.khwela.khwelacore.services.RequestService
import com.khwela.khwelacore.commands.RequestTripCommand
import com.khwela.khwelacore.events.DirectRequestAssignmentCompletedEvent
import com.khwela.khwelacore.events.TripRequestedEvent
import com.khwela.khwelacore.models.TripRequest
import com.khwela.khwelacore.services.OfferService
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle.apply
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import java.io.Serializable

@Aggregate
class Requests : Serializable {

    @AggregateIdentifier
    private var requestId: String? = null
    private var requestService: RequestService
    private var offerService: OfferService

    @CommandHandler
    constructor(command: RequestTripCommand, requestService: RequestService, offerService: OfferService) {
        this.requestService = requestService
        this.offerService = offerService

        val request = TripRequest(command.userId, command.pickup,
                command.destination, command.tripDate,
                command.numberOfPeople)
        request.id = command.tripRequestId
        requestService.save(request)

        apply(TripRequestedEvent(command.tripRequestId,
                command.userId, command.pickup, command.destination,
                command.tripDate, command.numberOfPeople, command.luggageSize))
    }

    @CommandHandler
    constructor(command: DirectRequestCommand, requestService: RequestService, offerService: OfferService) {
        this.requestService = requestService
        this.offerService = offerService

        var tripRequest = TripRequest(command.userId, command.pickup, command.destination, command.tripDate, command.numberOfPeople);
        tripRequest.id= command.tripRequestId;
        var tripId = command.tripId;
        this.requestService.attemptAssignment(tripId, tripRequest)

        apply(DirectRequestAssignmentCompletedEvent(requestId = command.tripRequestId, tripId = tripId))
    }

    @EventSourcingHandler
    fun on(event: TripRequestedEvent) {
        this.requestId = event.tripRequestId
    }

    @EventSourcingHandler
    fun on(event: DirectRequestAssignmentCompletedEvent) {
        this.requestId = event.requestId
        println("Direct assignment completed.. happy")
    }

    constructor(requestService: RequestService, offerService: OfferService) {
        this.requestService = requestService
        this.offerService = offerService
    }
}

