package com.khwela.khwelacore.aggregates

import com.khwela.khwelacore.services.RequestService
import com.khwela.khwelacore.commands.RequestTripCommand
import com.khwela.khwelacore.events.TripRequestedEvent
import com.khwela.khwelacore.models.TripRequest
import com.khwela.khwelacore.repositories.TripRepository
import com.khwela.khwelacore.repositories.TripRequestRepository
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
    private var tripRepository: TripRepository
    private var tripRequestRepository: TripRequestRepository
    private var requestService: RequestService


    @CommandHandler
    constructor(command: RequestTripCommand, tripRepository: TripRepository, tripRequestRepository: TripRequestRepository, requestService: RequestService) {
        this.tripRepository = tripRepository
        this.tripRequestRepository = tripRequestRepository
        this.requestService = requestService

        val request = TripRequest(command.userId, command.pickup,
                        command.destination, command.tripDate,
                        command.numberOfPeople)
        request.id = command.tripRequestId
        requestService.save(request)

        apply(TripRequestedEvent(command.tripRequestId,
                command.userId, command.pickup, command.destination,
                command.tripDate, command.numberOfPeople, command.luggageSize))
    }

    @EventSourcingHandler
    fun on(event: TripRequestedEvent) {
         this.requestId = event.tripRequestId
    }

    constructor(tripRepository: TripRepository, tripRequestRepository: TripRequestRepository, requestService: RequestService) {
        this.tripRepository = tripRepository
        this.tripRequestRepository = tripRequestRepository
        this.requestService = requestService
    }
}
