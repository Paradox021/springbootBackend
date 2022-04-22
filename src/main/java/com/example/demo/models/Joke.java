package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="joke")
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Long id;

    @Column(nullable = false)
    String text;

    public void setText(String text){
        this.text = text;
    }
}

// CREATE TABLE Joke (id long PRIMARY KEY AUTO_INCREMETENT, text VARCHAR(200) NOT NULL)