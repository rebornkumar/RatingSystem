package com.learn.repo;

import com.learn.entity.DriverRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRatingRepo extends JpaRepository<DriverRating,Integer> {

}
