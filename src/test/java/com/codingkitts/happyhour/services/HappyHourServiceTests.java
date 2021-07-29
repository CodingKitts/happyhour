package com.codingkitts.happyhour.services;

import com.codingkitts.happyhour.repos.HappyHourRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HappyHourServiceTests {

    @Autowired
    private HappyHourService happyHourService;

    @Mock
    private HappyHourRepository happyHourRepository;

    @Test
    public void testGetAllHappyHours_Success() throws Exception{

    }

    /*
        Think about the class under test. What are the expected input? Unexpected? Edge cases?

        Each function should have tests to validate their functionality.

        Be careful about the dependencies each test has, especially when there are helper function calls.
        Like do you need to mock the dependencies? Will the other test triggers trigger when we make a call inside a
        function call?
     */
}
