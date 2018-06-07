package com.khwela.khwelacore.eventhandling

import com.khwela.khwelacore.services.RequestService
import com.khwela.khwelacore.enums.TripRequestStatus.ASSIGNED
import com.khwela.khwelacore.events.RequestStatusUpdatedEvent
import com.khwela.khwelacore.events.TripRequestAssignedEvent
import com.khwela.khwelacore.events.TripRequestedEvent
import org.axonframework.eventhandling.EventBus
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventhandling.GenericEventMessage.asEventMessage
import org.springframework.stereotype.Component

@Component
@SuppressWarnings("unused")
class TripRequestedEventHandler(private val requestService: RequestService, private val eventBus: EventBus) {

    @EventHandler
    fun on(event: TripRequestAssignedEvent) {
        var updateStatus = requestService.updateRequestStatus(event.requestId, ASSIGNED)
        if (updateStatus > 0) {
            var requestStatusUpatedEvent = asEventMessage<RequestStatusUpdatedEvent>(RequestStatusUpdatedEvent(event.requestId, ASSIGNED))
            this.eventBus.publish(requestStatusUpatedEvent)
        }
    }

    @EventHandler
    fun on(event: TripRequestedEvent) {
         this.requestService.attemptAssignment()
    }

}
