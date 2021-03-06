package com.codingkitts.happyhour.repos;

import com.codingkitts.happyhour.entities.HappyHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HappyHourRepository extends JpaRepository<HappyHour, Long> {

    @Query(value = "SELECT * FROM happy_hour h WHERE (3961 * acos(cos(radians(:latitude)) * cos(radians(h.venue_lat)) * cos(radians(h.venue_lng) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(h.venue_lat)))) < :distance ORDER BY (6371 * acos(cos(radians(:latitude)) * cos(radians(h.venue_lat)) * cos(radians(h.venue_lng) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(h.venue_lat)))) DESC", nativeQuery = true)
    List<HappyHour> findHappyHoursWithinDistance(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("distance") double distance);
}
