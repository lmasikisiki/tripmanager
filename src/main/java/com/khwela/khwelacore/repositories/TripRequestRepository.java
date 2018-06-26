package com.khwela.khwelacore.repositories;

import com.khwela.khwelacore.enums.TripRequestStatus;
import com.khwela.khwelacore.models.TripRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TripRequestRepository extends MongoRepository<TripRequest, String> {

    @Query("UPDATE TripRequest t SET t.status = :status WHERE t.id = :id")
    int UpdateTripRequestStatus(@Param("id") String tripRequestId, @Param("status") TripRequestStatus newStatus);

    @Query("SELECT t FROM TripRequest t WHERE t.status =:status")
    Collection<TripRequest> getRequestsWithStatus(@Param("status") TripRequestStatus status);

}
