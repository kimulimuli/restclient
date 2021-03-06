package com.kimulimuli.restclient;

import static org.junit.Assert.*;

import java.time.Duration;

import com.kimulimuli.restclient.services.JokeService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JokeServiceTest {
    private Logger logger = LoggerFactory.getLogger(JokeServiceTest.class);
    @Autowired
    private JokeService service;

    @Test
    public void testGetJokeSync() {
        String joke = service.getJokeSync("Craig", "Walls");
        logger.info(joke);
        assertTrue(joke.contains("Craig") || joke.contains("Walls"));
    }

    @Test
    public void getJokeAsync() {
        String joke = service.getJokeAsync("Craig", "Walls").block(Duration.ofSeconds(2));
        logger.info(joke);
        assertTrue(joke.contains("Craig") || joke.contains("Walls"));
    }

    @Test
    public void useStepVerifier() {
        StepVerifier.create(service.getJokeAsync("Craig", "Walls")).assertNext(joke -> {
            logger.info(joke);
            assertTrue(joke.contains("Craig") || joke.contains("Walls"));
        }).verifyComplete();
    }

}