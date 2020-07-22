package com.learn.repo;

import com.learn.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RideRepo extends JpaRepository<Ride,Integer> {
}
