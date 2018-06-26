package com.khwela.khwelacore.repositories

import com.khwela.khwelacore.enums.TripStatus
import com.khwela.khwelacore.models.TripRecord
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TripRepository : MongoRepository<TripRecord, String> {


    @Query("UPDATE TripRecord t SET t.status = :status WHERE t.id=:id")
    fun UpdateTripStatus(@Param("id") id: String, @Param("status") newStatus: TripStatus)

    @Query("SELECT t FROM TripRecord t WHERE t.status =:status")
    fun getTripsWithStatus(@Param("status") newStatus: TripStatus): Collection<TripRecord>

    //@Query("SELECT t FROM TripRecord t WHERE t.status = :status  AND :parts")
   // fun getAvailableTrips(@Param("parts") queryParts: String, @Param("status") status: TripStatus = TripStatus.AVAILABLE):Collection<TripRecord>
}
