package com.codingkitts.happyhour.repos;

import com.codingkitts.happyhour.entities.HappyHourRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HappyHourRequestRepository extends JpaRepository<HappyHourRequest, Long> {
    List<HappyHourRequest> getHappyHourRequestsByHasBeenReadIsTrue();

    List<HappyHourRequest> getHappyHourRequestsByHasBeenReadIsFalse();

    @Modifying
    @Query("update HappyHourRequest h set h.hasBeenRead = true where h.happyHourRequestId = :happyHourRequestId")
    int readHappyHour(@Param("happyHourRequestId") Long happyHourRequestId);
}
