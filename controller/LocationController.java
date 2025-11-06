package com.app.controller;

import com.app.carsServices.GeolocationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
@RestController
@RequestMapping("api/v1/car")
public class LocationController {
    @Autowired
    private GeolocationService geolocationService;
    //http://localhost:8035/api/v1/car/get-location
    @GetMapping("/get-location")
    public String geoLocation(HttpServletRequest request) {
        String clintIp = getClintIp(request);
        String locationInfo = geolocationService.getLocation("27.7.31.130");
        return locationInfo;
    }
    private String getClintIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String xForwarderFor = request.getHeader("X-Forwarded-For");
        if(xForwarderFor != null && !xForwarderFor.isEmpty()) {
            remoteAddr = xForwarderFor.split(", ")[0];
        }
        return remoteAddr;
    }
}
