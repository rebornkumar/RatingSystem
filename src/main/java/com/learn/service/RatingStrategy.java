package com.learn.service;

public interface RatingStrategy {
    public void updatePassengerRating(int rideId,int rating,String remark);
    public void updateDriverRating(int rideId,int rating,String remark);
}
