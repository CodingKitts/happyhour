package com.codingkitts.happyhour.controllers;

import com.codingkitts.happyhour.entities.HappyHour;
import com.codingkitts.happyhour.services.HappyHourService;
import com.codingkitts.happyhour.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HappyHourControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HappyHourService happyHourService;

    //TODO: Update testGetAllHappyHours_Success() to include verifying the current User's Authentication
    //TODO: Update givenNoHappyHours_testGetAllHappyHours_Success() to include verifying the current User's Authentication.
    //TODO: Create a testGetAllHappyHours_Failure() to test failure when current User isn't Authenticated / Authorized. This is the only needed because we don't care if there are happy hours or not, just if the user is authorized.

    //GET TESTS
    @Test
    public void givenHappyHours_testGetAllHappyHours_Success() throws Exception {
        //Dummy Variables we'll simulate "Getting"
        HappyHour hh1 = new HappyHour(1L, "Restaurant 1", "Address 1", "Website 1", "This is Restaurant 1", 1.0, 1.0, "Mon Special", "Tue Special", "Wed Special", "Thur Special", "Fri Special", "Sat Special", "Sun Special");
        HappyHour hh2 = new HappyHour(2L, "Restaurant 2", "Address 2", "Website 2", "This is Restaurant 2", 2.0, 2.0, "Mon Special", "Tue Special", "Wed Special", "Thur Special", "Fri Special", "Sat Special", "Sun Special");

        //We create a List of Happy Hour objects from above. This is what we expect our function to return after calling it.
        List<HappyHour> hhours = Arrays.asList(hh1, hh2);


        //Now that we are prepared for the unit test call, lets tell our test what to do & expect
        //The when() will listen for the function call you pass in as a parameter (in this call happyHourService.getAllHappyHours())
        //The thenReturn() will return the list we created once the test hears the getAllHappyHours() call
        //The whole point of this call is to remove our dependency on the HappyHourService
        when(happyHourService.getAllHappyHours()).thenReturn(hhours);

        //This line will essentially do what line 52 does, but with an assertion tagged on that the HTTP status code is 200.
        mockMvc.perform(get("/specials"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void givenNoHappyHours_testGetAllHappyHours_Success() throws Exception {

        List<HappyHour> hhours = new ArrayList<>();
        when(happyHourService.getAllHappyHours()).thenReturn(hhours);

        mockMvc.perform(get("/specials"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testGetHappyHourById_Success() throws Exception {
        Optional<HappyHour> hh1 = Optional.of(new HappyHour(1L, "Restaurant 1", "Address 1", "Website 1", "This is Restaurant 1", 1.0, 1.0, "Mon Special", "Tue Special", "Wed Special", "Thur Special", "Fri Special", "Sat Special", "Sun Special"));

        when(this.happyHourService.getHappyHourById(1L)).thenReturn(hh1);

        mockMvc.perform(get("/specials/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.happyHourId").value(1L));
    }

    @Test
    public void testGetHappyHourById_NotFound() throws Exception {
    }

    /*@Test
    public void testGetAllHappyHours_FailureAuth() throws Exception {
    }*/

    //POST TESTS
    //Okay so let's think about the different tests I need to create for my POST methods.
    //TEST: Make sure Happy Hour is created Successfully
    //TEST: Make sure Happy Hour doesn't have an ID already

    @Test
    public void testCreateNewHappyHour() throws Exception {
        HappyHour hh1 = new HappyHour(1L,"Restaurant 1", "Address 1", "Website 1", "This is Restaurant 1", 1.0, 1.0, "Mon Special", "Tue Special", "Wed Special", "Thur Special", "Fri Special", "Sat Special", "Sun Special");

        when(happyHourService.createNewHappyHour(Mockito.any(HappyHour.class))).thenReturn(hh1);

        mockMvc.perform(post("/special")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJson(hh1))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.venueName").value("Restaurant 1"));
    }
}
