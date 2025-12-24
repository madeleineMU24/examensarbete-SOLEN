package com.example.sol_guide;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @DecimalMin(value = "-90", message = "Latitude cannot be smaller than -90")
    @DecimalMax(value = "90", message = "Latitude cannot be bigger than 90")
    private double latitude;

    @DecimalMin(value = "-180", message = "Longitude cannot be smaller than -180")
    @DecimalMax(value = "108", message = "Longitude cannot be bigger than 180")
    private double longitude;

    @Min(value = 0, message = "Deck direction needs to be at least 0°")
    @Max(value = 360, message = "Deck direction can max be 360°")
    private int deckDirection;

    @Min(value = 0, message = "Deck width needs to be at least 0°")
    @Max(value = 360, message = "Deck width can max be 360°")
    private int deckWidth;

    private boolean hasSun;

    public Restaurant() {
    }

    public Restaurant(Long id, String name, double latitude, double longitude, int deckDirection, int deckWidth, boolean hasSun) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.deckDirection = deckDirection;
        this.deckWidth = deckWidth;
        this.hasSun = hasSun;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getDeckDirection() {
        return deckDirection;
    }

    public void setDeckDirection(int deckDirection) {
        this.deckDirection = deckDirection;
    }

    public int getDeckWidth() {
        return deckWidth;
    }

    public void setDeckWidth(int deckWidth) {
        this.deckWidth = deckWidth;
    }

    public boolean isHasSun() {
        return hasSun;
    }

    public void setHasSun(boolean hasSun) {
        this.hasSun = hasSun;
    }
}
