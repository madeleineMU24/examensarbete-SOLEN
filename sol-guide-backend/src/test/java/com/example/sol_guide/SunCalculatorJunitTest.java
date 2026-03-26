package com.example.sol_guide;


import com.example.sol_guide.model.Restaurant;
import com.example.sol_guide.util.SunCalculator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SunCalculatorJunitTest {

    public SunCalculator sunCalc = new SunCalculator();

    @Test
    public void testIsHavingSun(){

        Restaurant testRestaurant = new Restaurant(1L, "Test", 59.3, 18.0, 180, 180, false);

        LocalDateTime time1 = LocalDateTime.of(2023, 6, 21, 13,30);
        LocalDateTime time2 = LocalDateTime.of(2023, 6, 21, 23,30);
        boolean result1 = sunCalc.isHavingSun(testRestaurant, time1);
        boolean result2 = sunCalc.isHavingSun(testRestaurant, time2);

        assertTrue(result1, "sol finns vid denna tid och plats");
        assertFalse(result2, "sol finns inte vid denna tid och plats");
    }

}
