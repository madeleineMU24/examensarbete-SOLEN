package com.example.sol_guide;


import com.example.sol_guide.model.Restaurant;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.http.MediaType;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
public class RestaurantIntegrationTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void testCreateRestaurant() throws Exception{

        Restaurant restaurant = new Restaurant(1L, "test", 50.0, 60.0, 180,180, true);


        mockMvc.perform(post("/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "test",
                        "latitude": 50.0,
                        "longitude": 60.0,
                        "deckDirection": 180,
                        "deckWidth": 180,
                        "hasSun": true
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.deckDirection").value(180));

    }


}