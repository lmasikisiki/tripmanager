package com.khwela.khwelacore.services

import com.khwela.khwelacore.commons.QueryBuilder
import com.khwela.khwelacore.models.TripRecord
import com.khwela.khwelacore.repositories.TripRepository
import org.axonframework.eventhandling.EventBus
import org.springframework.stereotype.Component
import javax.persistence.EntityManager

@Component
class OfferService(private  val tripRepository: TripRepository, private val eventBus :EventBus,private val em: EntityManager) {

    fun save(trip: TripRecord): TripRecord{
         return this.tripRepository.save(trip)
    }

    fun getAvailableTrips(filter : Map<String, Any>):List<TripRecord>{
       var queryparts = QueryBuilder.getQueryParts(filter).joinToString(" AND ")
        print("SELECT a FROM TripRecord a WHERE $queryparts");
       return em.createQuery("SELECT a FROM TripRecord a WHERE $queryparts").resultList
               as List<TripRecord>;
     }

}