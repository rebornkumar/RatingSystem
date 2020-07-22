package com.learn.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ride")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",updatable = false, nullable = false)
    private int id;
    @Column(name = "passenger_id")
    private int passengerId;
    @Column(name = "driver_id")
    private int driverId;
    @Column(name = "ride_status")
    private String rideStatus;
    @Column(name = "pickup_location")
    private String pickUpLocation;
    @Column(name = "drop_location")
    private String dropLocation;
}
