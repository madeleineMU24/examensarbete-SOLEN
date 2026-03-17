package com.example.sol_guide;

import com.example.sol_guide.controller.SunController;
import com.example.sol_guide.model.Restaurant;
import com.example.sol_guide.service.JwtService;
import com.example.sol_guide.service.SunService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import java.util.Optional;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SunController.class)
public class SunControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SunService sunService;

    @MockitoBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetSunById() throws Exception{
        Restaurant restaurant = new Restaurant(1L, "Test", 50.0, 60.0, 180, 180, false);
        when(sunService.findRestaurantById(1L)).thenReturn(Optional.of(restaurant));

        mockMvc.perform(get("/restaurant/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    @WithMockUser("ADMIN")
    public void testPostCreateRestaurant() throws Exception {

        Restaurant restaurant = new Restaurant(null, "Test", 50.0, 60.0, 180,180, false);
        when(sunService.createRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        mockMvc.perform(post("/restaurant")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(restaurant)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test"))
                .andExpect(jsonPath("$.latitude").value(50.0));

    }
}
