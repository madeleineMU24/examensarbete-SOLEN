package com.example.sol_guide;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SunServiceTest {

    @Mock
    private SunRepository sunRepo;

    @InjectMocks
    private SunService sunService;

    @Test
    public void testGetRestaurantById(){
        Restaurant restaurant = new Restaurant(1L, "Test", 50.0, 60.0, 180, 180, false);
        when(sunRepo.findById(1L)).thenReturn(Optional.of(restaurant));

        Restaurant result = sunService.findRestaurantById(1L).orElse(null);

        assertEquals("Test", result.getName());
        verify(sunRepo).findById(1L);
    }


}
