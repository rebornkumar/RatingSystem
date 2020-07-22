package com.learn.serviceImpl;

import com.learn.entity.AggregatedRating;
import com.learn.entity.Ride;
import com.learn.enums.RideStatus;
import com.learn.repo.AggregatedRatingRepo;
import com.learn.repo.RideRepo;
import com.learn.service.RatingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
public class ApplicationDriver {
    @Autowired
    private RideRepo rideRepo;
    @Autowired
    private RatingStrategyImpl ratingStrategy;
    @Autowired
    private AggregatedRatingRepo aggregatedRatingRepo;

    private static final Logger logger = LoggerFactory.getLogger(ApplicationDriver. class);

    public void start(int totalRide) {
        createRide(totalRide);
        startRatingSystem();
        getAggregatedRating();
    }
    private void createRide(int totalRide) {
        for(int i = 0;i < totalRide;i++) {
            Ride ride = new Ride();
            int passengerId = 1003 * (i+1);
            int driverId = 1003 * (10*(i+1));
            ride.setDriverId(driverId);
            ride.setPassengerId(passengerId);
            ride.setPickUpLocation("from : " + Integer.toString(passengerId));
            ride.setDropLocation(", to : "+Integer.toString(+passengerId));
            ride.setRideStatus(RideStatus.COMPLETE.toString());
            if(i%3 == 0) {
                ride.setRideStatus(RideStatus.IN_PROGRESS.toString());
            }
            rideRepo.save(ride);
        }
    }
    private void startRatingSystem() {
        List<Ride>rideList = rideRepo.findAll();
        log.info("total rides : " + rideList.size());
        Random random = new Random();
        for(Ride ride : rideList) {
            int passengerRating = random.nextInt(5);
            int driverRating = random.nextInt(5);
            logger.info("update passenger rating for passengerID : {}, rideId : {}", ride.getPassengerId(),ride.getId());
            ratingStrategy.updatePassengerRating(ride.getId(),passengerRating,"");
            logger.info("update driver rating for driverId : {},  rideId : {}", ride.getDriverId(), ride.getId());
            ratingStrategy.updateDriverRating(ride.getId(),driverRating,"");
        }
    }
    public void getAggregatedRating() {
        List<AggregatedRating>aggregatedRatings = aggregatedRatingRepo.findAll();
        for(AggregatedRating aggregatedRating : aggregatedRatings) {
            log.info("aggregated rating info : {}" + aggregatedRating.toString());
        }
    }
}
