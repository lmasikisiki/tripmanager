package com.khwela.khwelacore.aggregates

import com.khwela.khwelacore.commands.CancelTripCommand
import com.khwela.khwelacore.commands.OfferTripCommand
import com.khwela.khwelacore.events.TripCancelledEvent
import com.khwela.khwelacore.events.TripOfferedEvent
import com.khwela.khwelacore.models.TripRecord
import com.khwela.khwelacore.services.OfferService
import com.khwela.khwelacore.services.RequestService
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate

import java.io.Serializable

import org.axonframework.commandhandling.model.AggregateLifecycle.*

@Aggregate
class Trip : Serializable {

    @AggregateIdentifier
    private var tripIdentifier: String? = null
    private val offerService : OfferService
    private val requestService: RequestService

    @CommandHandler
    constructor(command: OfferTripCommand, offerService: OfferService, requestService: RequestService) {
        this.offerService = offerService
        this.requestService = requestService
        var trip = TripRecord(command.tripId,
                command.offeredBy, command.pickup,
                command.destination,
                command.numberOfSeats, command.tripDate)
        this.offerService.save(trip)
        apply(TripOfferedEvent(command.tripId,
                command.offeredBy,
                command.pickup,
                command.destination,
                command.numberOfSeats,
                command.tripDate))
    }

    @CommandHandler
    constructor(command: CancelTripCommand, offerService: OfferService, requestService: RequestService) {
        apply(TripCancelledEvent(command.tripId,
                command.userId,
                command.cancellationReason))
        this.offerService = offerService
        this.requestService = requestService
    }

    @EventSourcingHandler
    fun on(event: TripOfferedEvent) {
        this.tripIdentifier = event.tripId
    }

    @EventSourcingHandler
    fun on(event: TripCancelledEvent) {
        this.tripIdentifier = event.tripId
    }

    constructor(offerService: OfferService, requestService: RequestService) {
        this.offerService = offerService
        this.requestService = requestService
    }
}
