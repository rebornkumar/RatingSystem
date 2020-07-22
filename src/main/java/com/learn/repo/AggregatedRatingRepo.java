package com.learn.repo;

import com.learn.entity.AggregatedRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AggregatedRatingRepo extends JpaRepository<AggregatedRating,Integer> {

    @Query(value = "select * from aggregated_rating where user_id = ?1",nativeQuery = true)
    Optional<AggregatedRating> findByUserId(int passengerId);
}
