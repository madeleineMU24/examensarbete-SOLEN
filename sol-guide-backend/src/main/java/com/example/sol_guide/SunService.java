package com.example.sol_guide;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SunService {

    private final SunRepository sunRepo;
    private final SunCalculator sunCalc;

    public SunService(SunRepository sunRepo, SunCalculator sunCalc){
        this.sunRepo = sunRepo;
        this.sunCalc = sunCalc;
    }
    public boolean restaurantExist(Long id){
        return sunRepo.existsById(id);
    }

    public List<Restaurant> findAllRestaurants(){
        return sunRepo.findAll();
    }

    public Optional<Restaurant> findRestaurantById(Long id){
        return sunRepo.findById(id);
    }

    public Restaurant createRestaurant(Restaurant restaurant){
        return sunRepo.save(restaurant);
    }

    public List<Restaurant> getSunnyRestaurants(LocalDateTime time){
        return sunRepo.findAll().stream()
                .filter(r -> sunCalc.isHavingSun(r, time))
                .toList();
    }

    public void updateSunStatus(Restaurant restaurant, LocalDateTime time){
        boolean hasSun = sunCalc.isHavingSun(restaurant, time);
        restaurant.setHasSun(hasSun);
    }


    public List<Restaurant>getFilteredRestaurants(LocalDateTime dateAndTime, Boolean includeNoSun){
        List<Restaurant> allRestaurants = sunRepo.findAll();
        List<Restaurant> filteredRestaurants = new ArrayList<>();

        for(Restaurant restaurant : allRestaurants){
            updateSunStatus(restaurant, dateAndTime);

            if (Boolean.TRUE.equals(includeNoSun) || restaurant.isHasSun()) {
                filteredRestaurants.add(restaurant);
            }
        }return filteredRestaurants;

    }


    public void deleteRestaurantById(Long id){
        if(!restaurantExist(id)){
            throw new EntityNotFoundException("Restaurant with id: " + id + " does not exist");
        }
        sunRepo.deleteById(id);
    }

}
