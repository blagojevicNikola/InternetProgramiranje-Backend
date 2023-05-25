package com.example.ip_etfbl_api.controllers;

import com.example.ip_etfbl_api.models.responses.Location;
import com.example.ip_etfbl_api.services.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<Location> getAllLocations()
    {
        return locationService.findAll(Location.class);
    }

}
