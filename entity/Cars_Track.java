package com.app.entity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//http://localhost:8030/api/v1/auth/cars
@RestController
@RequestMapping("/api/v1/cars")
public class Cars_Track {
    @PostMapping("/add-car")
    public String addCars() {
        return "added";
    }
}
