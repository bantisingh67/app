package com.app.carsServices;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
    @Service
    public class GeolocationService {

        private static final String API_KEY="edd6d8cd51f81b";
        private static final String URL="http://ip-api.com/json/";

        public String getLocation(String ipAddress) {
            RestTemplate restTemplate = new RestTemplate();
            String url = URL + ipAddress + "?access_key="+API_KEY;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        }
    }

