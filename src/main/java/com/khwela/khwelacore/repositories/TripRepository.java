package com.khwela.khwelacore.repositories;

import com.khwela.khwelacore.models.TripRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<TripRecord, String> {
}
