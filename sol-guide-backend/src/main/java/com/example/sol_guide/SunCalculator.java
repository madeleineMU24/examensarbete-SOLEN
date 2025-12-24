package com.example.sol_guide;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SunCalculator {

    public boolean isHavingSun (Restaurant restaurant, LocalDateTime time){
        double latitude = restaurant.getLatitude();
        double longitude = restaurant.getLongitude();
        int deckDirection = restaurant.getDeckDirection();
        int deckWidth = restaurant.getDeckWidth();

        int dayOfYear = time.getDayOfYear();
        double hour = time.getHour() + time.getMinute() / 60;

        double declination = 23.45 * Math.sin(Math.toRadians(360.0 *(284 + dayOfYear) / 365));

        double hourAngle = 15 * (hour - 12);

        double elevation = Math.toDegrees(
                Math.asin(
                        Math.sin(Math.toRadians(latitude)) * Math.sin(Math.toRadians(declination)) +
                                Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(declination)) *
                                        Math.cos(Math.toRadians(hourAngle))
                )
        );
        if (elevation <= 0) return false;

        double azimuth = Math.toDegrees(
                Math.acos(
                        (Math.sin(Math.toRadians(declination))- Math.sin(Math.toRadians(latitude)) * Math.sin(Math.toRadians(elevation))) /
                                (Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(elevation)))
                )
        );
        if (hourAngle > 0) {
            azimuth = 360 - azimuth;
        }

        if (deckWidth >= 360) return true;

        double leftEdge = (deckDirection - deckWidth / 2 + 360) % 360;
        double rightEdge = (deckDirection + deckWidth / 2) % 360;


        boolean hitsDeck;

        if (leftEdge < rightEdge) {
            hitsDeck = azimuth >= leftEdge && azimuth <= rightEdge;
        }else{
            hitsDeck = azimuth >= leftEdge || azimuth <= rightEdge;
        }
        return hitsDeck;
    }
}
