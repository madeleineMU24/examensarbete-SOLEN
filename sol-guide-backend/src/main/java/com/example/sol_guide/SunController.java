package com.example.sol_guide;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@RequestMapping("/restaurant")
@RestController
public class SunController {

    SunService sunService;

    public SunController(SunService sunService){
        this.sunService = sunService;
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant (@Valid @RequestBody Restaurant restaurant) {
        Restaurant result = sunService.createRestaurant(restaurant);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Restaurant> findAllRestaurants(){
        return sunService.findAllRestaurants();
    }

    @GetMapping("/sun") //får alla restauranger som har sol, inte dom utan
    public List<Restaurant> sunnyRestaurants(@RequestParam String date, @RequestParam String time){
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);
        LocalDateTime dateAndTime = LocalDateTime.of(localDate, localTime);

        return sunService.getSunnyRestaurants(dateAndTime);
    }

    @GetMapping("/sunny")
    public List<Restaurant> sunnyAndNotSunnyRestaurants(@RequestParam String date, @RequestParam String time,
                                                        @RequestParam(required = false)
                                                        Boolean includeNoSun){
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);

        LocalDateTime dateAndTime = LocalDateTime.of(localDate, localTime);

        return sunService.getFilteredRestaurants(dateAndTime, includeNoSun);
    }


    @GetMapping("/{id}")
    public Restaurant findRestaurantById(@PathVariable Long id){
        return sunService.findRestaurantById(id).orElseThrow(() -> new EntityNotFoundException("No restaurant with id: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurantById(@PathVariable Long id){
        sunService.deleteRestaurantById(id);
        return ResponseEntity.noContent().build();
    }
}
