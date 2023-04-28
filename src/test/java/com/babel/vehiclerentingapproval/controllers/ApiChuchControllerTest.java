package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.services.ChuckNorrisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertEquals;


public class ApiChuchControllerTest {

    ChuckNorrisService chuckNorrisService;

    @BeforeEach
    void setupAll(){
    }

    @Test
    void TestGettingRandomJoke () {
        ChuckNorrisService chuckNorrisService = Mockito.mock(ChuckNorrisService.class);
        ApiChuckController apiChuckController = new ApiChuckController(chuckNorrisService);
        Mockito.when(chuckNorrisService.getRandomJoke()).thenReturn("");

        ResponseEntity response = apiChuckController.getRandomJoke();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
