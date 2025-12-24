/*package com.example.sol_guide;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webclient.test.autoconfigure.AutoConfigureWebClient;

import org.springframework.test.web.reactive.server.WebTestClient;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.test.context.ActiveProfiles;



@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebClient
public class RestaurantIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void testCreateRestaurant(){

        Restaurant restaurant = new Restaurant(null, "test", 50.0, 60.0, 180,180);


        webTestClient.post()
                        .uri("/restaurant")
                        .bodyValue(restaurant)
                        .exchange()
                        .expectStatus().isCreated()
                        .expectBody()
                        .jsonPath("$.name").isEqualTo("test")
                        .jsonPath("$deckDirection").isEqualTo(180);

    }


}*/