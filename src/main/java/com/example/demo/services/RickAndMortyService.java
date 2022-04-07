package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.models.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RickAndMortyService {
    @Autowired
    RestTemplate restTemplate;

    public Person getCharacterFromAPI(){
        long randomNumber = Math.round(Math.random()*826)+1;
        String url = "https://rickandmortyapi.com/api/character/"+randomNumber;
        Person character = restTemplate.getForObject(url, Person.class);
        return character;
    }

    public ArrayList<Person> getAllCharacterFromAPI(){
        ArrayList<Person> personajes = new ArrayList<Person>();
        for(int i=1; i<827; i++ ){
            String url = "https://rickandmortyapi.com/api/character/"+i;
            personajes.add(restTemplate.getForObject(url, Person.class));
        }
        return personajes;
    }
}
