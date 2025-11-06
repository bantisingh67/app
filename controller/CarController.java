package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
@RequestMapping("api/v1/car")
public class CarController {

    //http://localhost:8035/api/v1/car/ipCode
   @GetMapping("/ipCode")
    public ResponseEntity<String> getMessage() {
        RestTemplate restTemplete = new RestTemplate();
        ResponseEntity<String> myIpAddress =
                restTemplete.getForEntity("https://api.ipify.org?format=json", String.class);
        return myIpAddress;
    }
}
