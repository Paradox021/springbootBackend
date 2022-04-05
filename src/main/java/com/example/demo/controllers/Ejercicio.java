package com.example.demo.controllers;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

import com.example.demo.utils.Utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ejercicio {
    
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
}
