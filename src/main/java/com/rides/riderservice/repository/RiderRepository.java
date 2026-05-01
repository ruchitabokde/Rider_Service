package com.rides.riderservice.repository;

import com.rides.riderservice.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {

    boolean existsByEmail(String email);

    @Query("select coalesce(max(r.riderId), 0) from Rider r")
    Long findMaxRiderId();

}
