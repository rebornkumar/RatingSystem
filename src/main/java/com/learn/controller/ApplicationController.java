package com.learn.controller;

import com.learn.serviceImpl.ApplicationDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class ApplicationController {
    @Autowired
    private ApplicationDriver applicationDriver;
    @GetMapping(value = "/ride")
    public void createRide(@RequestParam int totalRide) {
        applicationDriver.start(totalRide);
    }
}
