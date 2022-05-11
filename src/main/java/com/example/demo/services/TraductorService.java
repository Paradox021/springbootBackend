package com.example.demo.services;

import com.example.demo.models.TraducedText;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class TraductorService {
    @Autowired
    RestTemplate restTemplate;

    public TraducedText getTraductionFromAPI(String cad){
        String url = "https://api.mymemory.translated.net/get?q="+cad+"&langpair=es|en";
        TraducedText text = restTemplate.getForObject(url, TraducedText.class);
        return text;
    }
}
