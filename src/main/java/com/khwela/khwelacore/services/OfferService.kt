package com.khwela.khwelacore.services

import com.khwela.khwelacore.models.TripRecord
import com.khwela.khwelacore.repositories.TripRepository
import org.axonframework.eventhandling.EventBus
import org.springframework.stereotype.Component

@Component
class OfferService(private  val tripRepository: TripRepository, private val eventBus :EventBus) {

    fun save(trip: TripRecord): TripRecord{
         return this.tripRepository.save(trip)
    }

}