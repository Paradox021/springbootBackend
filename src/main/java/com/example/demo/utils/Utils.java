package com.example.demo.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.type.CharacterType;

public class Utils {
    public static boolean isPalindrome(String value){
        value = value.replace(" ", "");
        StringBuilder builder =new StringBuilder(value);
        String reversedName = builder.reverse().toString();
        return value.equals(reversedName);
    }

    public static void save(String fileName, String text) throws IOException{
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(fileName, true);
            pw = new PrintWriter(fw);
            pw.print(text);
        } finally{
            if(pw!=null) pw.close();
        }
    }
    public static boolean remove(String name){
        File file = new File(name);
        boolean result = file.delete();    
        return result;
    }

    public static int[] contar(String cad){
        String vocales = "aeiou";
        String consonantes = "bcdfghjklmnñpqrstvwxyz";
        int[] num = {0, 0};
        int cadLength = cad.length();
        for(int i = 0; i<cadLength; i++){
            String letra = Character.toString(cad.charAt(i));
            if(vocales.contains(letra)) num[0]++;
            if(consonantes.contains(letra)) num[1]++;
        }
        return num;
    }

    public static String transformaTexto(String texto){
        char[] cadena = texto.toCharArray();
        for(int i=0; i<cadena.length; i++){
            if(Math.random()<0.5) cadena[i] = Character.toUpperCase(cadena[i]);           
        }
        texto = cadena.toString();
        return texto; 
    }
}
