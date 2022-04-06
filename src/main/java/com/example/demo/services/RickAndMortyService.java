package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

class Character{
    public String name;
    public String image;
}

@Service
public class RickAndMortyService {
    @Autowired
    RestTemplate restTemplate;

    public Character getCharacterFromAPI(){
        long randomNumber = Math.round(Math.random()*826)+1;
        String url = "https://rickandmortyapi.com/api/character/"+randomNumber;
        Character character = restTemplate.getForObject(url, Character.class);
        return character;
    }
}
