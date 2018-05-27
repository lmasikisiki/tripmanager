package com.khwela.khwelacore.repositories;

import com.khwela.khwelacore.enums.TripRequestStatus;
import com.khwela.khwelacore.models.TripRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRequestRepository extends JpaRepository<TripRequest, Integer> {

    @Query("UPDATE TripRequest t SET t.status = :status WHERE t.id = :id")
    @Modifying
    void UpdateTripRequestStatus(@Param("id") int tripRequestId, @Param("status") TripRequestStatus newStatus);

}
