package com.khwela.khwelacore.repositories;

import com.khwela.khwelacore.enums.TripStatus;
import com.khwela.khwelacore.models.TripRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TripRepository extends JpaRepository<TripRecord, String> {

     @Modifying
    @Query("UPDATE TripRecord t SET t.status = :status WHERE t.id=:id")
    void UpdateTripStatus(@Param("id") String  id, @Param("status") TripStatus newStatus);

    @Query("SELECT t FROM TripRecord t WHERE t.status =:status")
    Collection<TripRecord> getTripsWithStatus(@Param("status") TripStatus newStatus);
}
