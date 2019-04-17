package com.kimulimuli.restclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kimulimuli.restclient.json.JokeResponse;

@Service
public class JokeService {

    private RestTemplate template;

    @Autowired
    public JokeService(RestTemplateBuilder builder) {
        template = builder.build();
    }

    public String getJokeSync(String first, String last) {

        String base = "http://api.icndb.com/jokes/random?limitTo=[nerdy]";
        String url = String.format("%s&firstName=%s&lastName=%s", base, first, last);

        return template.getForObject(url, JokeResponse.class).getValue().getJoke();
    }
}