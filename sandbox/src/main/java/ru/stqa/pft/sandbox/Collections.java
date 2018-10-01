package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
       String[] langs = {"Java", "C","Phyton","Groovy"};
        List<String> languages= Arrays.asList("Английский", "Итальянский","Японский","Французский");

//Вариант 1
       for (int i=0; i<langs.length; i++){
           System.out.println("Я хочу выучить " + langs[i] );
       }

//Вариант 2
        for (String l: languages){
            System.out.println("Я хочу выучить " + l);
        }
//Вариант 1
        for (int i=0; i<languages.size(); i++){
            System.out.println("Я хочу выучить " + languages.get(i));
        }
    }
}
