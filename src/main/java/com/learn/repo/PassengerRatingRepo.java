package com.learn.repo;

import com.learn.entity.PassengerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRatingRepo extends JpaRepository<PassengerRating,Integer> {
    
}
