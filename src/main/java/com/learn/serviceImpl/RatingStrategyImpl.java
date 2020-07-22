package com.learn.serviceImpl;

import com.learn.entity.AggregatedRating;
import com.learn.entity.DriverRating;
import com.learn.entity.PassengerRating;
import com.learn.entity.Ride;
import com.learn.enums.RideStatus;
import com.learn.enums.UserType;
import com.learn.repo.AggregatedRatingRepo;
import com.learn.repo.DriverRatingRepo;
import com.learn.repo.PassengerRatingRepo;
import com.learn.repo.RideRepo;
import com.learn.service.RatingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static com.learn.enums.UserType.PASSENGER;

@Service
@Slf4j
public class RatingStrategyImpl implements RatingStrategy {

    @Autowired
    private PassengerRatingRepo passengerRatingRepo;
    @Autowired
    private DriverRatingRepo driverRatingRepo;
    @Autowired
    private RideRepo rideRepo;
    @Autowired
    private AggregatedRatingRepo aggregatedRatingRepo;

    @Override
    public void updatePassengerRating(int rideId,int rating,String remark) {
        Optional<Ride>optionalRide = rideRepo.findById(rideId);
        if(optionalRide.isPresent() && optionalRide.get().getRideStatus().equals(RideStatus.COMPLETE.toString())) {
            Ride ride = optionalRide.get();
            PassengerRating passengerRating = new PassengerRating();
            passengerRating.setRideId(rideId);
            passengerRating.setDriverId(ride.getDriverId());
            passengerRating.setPassengerId(ride.getPassengerId());
            passengerRating.setRating(rating);
            passengerRating.setRemark(remark);
            passengerRatingRepo.save(passengerRating);
            log.info("passenger saved in database passengerId : {}", ride.getPassengerId());
            updateAggregatedRating(ride.getPassengerId(),rating,UserType.PASSENGER);
        }
        else {
            log.error("ride with rideId : {} ,doest not exist in database or have status : {}",rideId , RideStatus.IN_PROGRESS);
        }
    }
    @Override
    public  void updateDriverRating(int rideId,int rating,String remark) {
        Optional<Ride>optionalRide = rideRepo.findById(rideId);
        if(optionalRide.isPresent() && optionalRide.get().getRideStatus().equals(RideStatus.COMPLETE.toString())) {
            Ride ride = optionalRide.get();
            DriverRating driverRating = new DriverRating();
            driverRating.setRideId(rideId);
            driverRating.setDriverId(ride.getDriverId());
            driverRating.setPassengerId(ride.getPassengerId());
            driverRating.setRating(rating);
            driverRating.setRemark(remark);
            driverRatingRepo.save(driverRating);
            log.info("driver saved in database driverId : {}", ride.getDriverId());
            updateAggregatedRating(ride.getDriverId(),rating, UserType.DRIVER);
        }
        else {
            log.error("ride with rideId : {} ,doest not exist in database or have status : {}",rideId , RideStatus.IN_PROGRESS);
        }
    }
    private void updateAggregatedRating(int userId,int rating, UserType userType) {

        Optional<AggregatedRating> optionalAggregatedRating = aggregatedRatingRepo.findByUserId(userId);

        if(optionalAggregatedRating.isPresent()) {
            log.info("update aggregated rating for userId : {} ,userType : {}",userId,userType.toString());
            optionalAggregatedRating.get().setAverageRating( optionalAggregatedRating.get().getAverageRating()* optionalAggregatedRating.get().getTotalRide() + rating);
            optionalAggregatedRating.get().setTotalRide( optionalAggregatedRating.get().getTotalRide() + 1);
            optionalAggregatedRating.get().setAverageRating( optionalAggregatedRating.get().getAverageRating() /  optionalAggregatedRating.get().getTotalRide());
            optionalAggregatedRating.get().setAverageRating(Math.round(optionalAggregatedRating.get().getAverageRating()*100d)/100d);
            aggregatedRatingRepo.save(optionalAggregatedRating.get());
        }else {
            log.info("create aggregated rating for userId : {}, userType : {}",userId, userType.toString());
            AggregatedRating  aggregatedRating = new AggregatedRating();
            aggregatedRating.setUserId(userId);
            aggregatedRating.setAverageRating((double)rating);
            aggregatedRating.setUserType(userType.toString());
            aggregatedRating.setTotalRide(1);
            aggregatedRatingRepo.save(aggregatedRating);
        }
    }
}
