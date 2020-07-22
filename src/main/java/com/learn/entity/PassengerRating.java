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
@Table(name = "passenger_rating")
public class PassengerRating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",updatable = false, nullable = false)
    private int id;
    @Column(name = "passenger_id")
    private int passengerId;
    @Column(name = "driver_id")
    private int driverId;
    @Column(name = "ride_id")
    private int rideId;
    @Column(name = "rating")
    private int rating;
    @Column(name = "remark")
    private String remark;
}