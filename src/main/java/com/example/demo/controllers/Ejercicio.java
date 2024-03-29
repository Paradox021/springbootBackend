package com.example.demo.controllers;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Map;

import com.example.demo.models.Joke;
import com.example.demo.models.Person;
import com.example.demo.models.Pet;
import com.example.demo.models.TranslatedText;
import com.example.demo.services.JokeService;
import com.example.demo.services.PetService;
import com.example.demo.services.RickAndMortyService;
import com.example.demo.services.TranslatorService;
import com.example.demo.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ejercicio {

    @Autowired
    RickAndMortyService rickAndMortyService;

    @Autowired
    JokeService jokeService;

    @Autowired
    PetService petService;

    @Autowired
    TranslatorService translatorService;
    
    // http://localhost:8080/
    @GetMapping("/")
    public String greet(){
        return "Bienvenido al servidor backend";
    }

    // http://localhost:8080/aleatorio
    @GetMapping("/aleatorio")
    public String numRandom(){
        long r = Math.round(Math.random()*100);
        return r + "";
    }

    // http://localhost:8080/palindromo
    @GetMapping("/palindromo/{name}")
    public String palindrome(@PathVariable String name){
        boolean palindrome = Utils.isPalindrome(name);
        return palindrome ? "Si es palindromo":"No es palindromo";
    }

    // http://localhost:8080/sumar?num1=5&num2=10
    @GetMapping("/sumar")
    public String add(@RequestParam String num1, @RequestParam String num2){
        int resultado = Integer.parseInt(num1) + Integer.parseInt(num2);
        Object params[] = {num1, num2, resultado};
        return MessageFormat.format("La suma de {0} y {1} es {2} " , params);
    }

    
    @PostMapping("/saveProductOnDisk")
    public String saveProductOnDisk(@RequestParam Map<String,String> body){
        String ArticleValue = body.get("article");
        String priceValue = body.get("price");
        
        //valido si no son elementos vacios
        if(ArticleValue.equals("") || priceValue.equals("")){
            return "Error,datos incorrectos";
        }
        // precio negativo
        if(Integer.valueOf(priceValue) < 0){
            return "Precio negativo";
        }
        try {
            Utils.save("datos.txt", ArticleValue+","+priceValue+"\n");
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al escribir en disco";
        }
       // Devolver msg cliente
       return "Producto guardado correctamente";
       
    }  

    @DeleteMapping("/removeFile")
    public String removeFile(){
        boolean result = Utils.remove("datos.txt");
        return result?"Borrado correctamente":"No se puede borrar";
    }

    // http://localhost:8080/rickandmorty/random
    @GetMapping("/rickandmorty/random")
    public String getRickAndMorty(){
        Person c = rickAndMortyService.getCharacterFromAPI();
        return "<img src='"+c.image+"'/>";
    }

    @GetMapping("/rickandmorty/list")
    public String getRickAndMortyList(){
        ArrayList<Person> persons = rickAndMortyService.getCharactersFromAPI();
        String result="";
        for (Person c: persons) {
            result += "<img src='"+c.image+"'/>";
        }
        return result;
    }   

    // http://localhost:8080/listarChiste
    @GetMapping("/listarChistes")
    public String jokeList(){
        ArrayList<Joke> jokes = jokeService.getAllJokes();
        String listado = "";
        for(Joke joke : jokes){
            listado += joke.getText();
            listado += "<br/>";
        }
        return listado;
    }

    // http://localhost:8080/insertarChiste
    @PostMapping("/insertarChiste")
    public String addJoke(@RequestParam Map<String,String> body){
        //insert into joke(text) values ("texto")
        String jokeText = body.get("text");
        Joke joke = new Joke();
        joke.setText(jokeText);
        jokeService.saveJoke(joke);
        return "Chiste creado correctamente";
    }
    
    // http://localhost:8080/contar/????
    @GetMapping("/contar/{texto}")
    public String contar(@PathVariable String texto){
        int[] numLetras = Utils.contar(texto); 
        texto = MessageFormat.format("{0} tiene {1} vocales y {2} consonantes" , texto, numLetras[0], numLetras[1]);
        return texto;
    }
    

    // http://localhost:8080/listar
    @GetMapping("/listar")
    public String petList(){
        ArrayList<Pet> pets = petService.getAllPets();
        String listado = "";
        for(Pet pet : pets){
            listado += pet.getName();
            listado += "<br/>";
        }
        return listado;
    }

    // http://localhost:8080/guarda
    @GetMapping("/guarda")
    public String addPet(@RequestParam String nombre){
        //insert into joke(text) values ("texto")
        Pet pet = new Pet();
        pet.setName(nombre);
        petService.savePet(pet);
        return "Mascota registrada correctamente";
    }
    
    // http://localhost:8080/????
    @GetMapping("/{texto}")
    public String frase(@PathVariable String texto){
        texto = Utils.transformaTexto(texto);
        return texto;
    }

    // http://localhost:8080/traduce/????
    @GetMapping("/traduce/{texto}")
    public String getTranslation(@PathVariable String texto){
        TranslatedText t = translatorService.getTranslationFromAPI(texto);
        return t.responseData.translatedText;
    }

}
