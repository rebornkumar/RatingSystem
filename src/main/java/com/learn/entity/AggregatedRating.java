package com.learn.entity;

import com.learn.enums.UserType;
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
@Table(name = "aggregated_rating")
public class AggregatedRating {
    @Id
    @Column(name = "user_id",nullable = false)
    private int userId;
    @Column(name = "user_type")
    private String userType;
    @Column(name = "average_rating",precision = 2)
    private double averageRating;
    @Column(name = "total_ride")
    private int totalRide;

    @Override
    public String toString() {
        return "userType : " + this.userType.toString() + " userId : " + this.userId + ", totalRide : " + this.totalRide + ", averageRating : " + this.averageRating;
    }
}
