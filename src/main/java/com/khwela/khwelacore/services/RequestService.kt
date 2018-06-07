package com.khwela.khwelacore.services

import com.khwela.khwelacore.commons.CommonUtilities
import com.khwela.khwelacore.enums.TripRequestStatus
import com.khwela.khwelacore.enums.TripStatus
import com.khwela.khwelacore.events.TripRequestAssignedEvent
import com.khwela.khwelacore.models.TripRecord
import com.khwela.khwelacore.models.TripRequest
import com.khwela.khwelacore.repositories.TripRepository
import com.khwela.khwelacore.repositories.TripRequestRepository
import org.axonframework.eventhandling.EventBus
import org.axonframework.eventhandling.GenericEventMessage
import org.springframework.stereotype.Component

@Component
class RequestService(private val eventBus: EventBus, private val requestRepository: TripRequestRepository, private val tripRepository: TripRepository) {

    fun save(request: TripRequest) {
        requestRepository.save(request)
    }

    fun updateRequestStatus(requestId: String, status: TripRequestStatus): Int {
        return requestRepository.UpdateTripRequestStatus(requestId, status)
    }

    fun attemptAssignment() {
        val tripRequests = requestRepository.getRequestsWithStatus(TripRequestStatus.OPEN)
        if (!tripRequests.isEmpty()) {
            tripRequests.forEach { request ->
                val tripRecord = getAvailableTripMatching(request)
                if (tripRecord != null) {
                    assignRequestToTrip(request, tripRecord)
                }
            }
        }
    }

    fun attemptAssignment(tripId: String, tripRequest: TripRequest) {
        var request = requestRepository.save(tripRequest)
        var trip = tripRepository.findById(tripId).get();
        if (request != null && trip != null) {
            assignRequestToTrip(request, trip)
        }
    }

    private fun assignRequestToTrip(request: TripRequest, tripRecord: TripRecord) {
        val tripUsers = tripRecord.users
        tripUsers.add(request.userId)
        tripRecord.users = tripUsers
        var saved = tripRepository.save(tripRecord)
        val tripRequestAssignedEvent = GenericEventMessage(TripRequestAssignedEvent(request.id, saved.id))
        eventBus.publish(tripRequestAssignedEvent)
    }

    private fun getAvailableTripMatching(request: TripRequest): TripRecord? {
        return tripRepository.getTripsWithStatus(TripStatus.AVAILABLE).firstOrNull({ trip ->
            trip.status == TripStatus.AVAILABLE &&
                    CommonUtilities.travelOnSameDay(request.tripDate, trip.tripDate) &&
                    request.destination === trip.destination &&
                    request.numberOfPeople <= trip.numberOfSeats - trip.users.size
        })
    }
}

