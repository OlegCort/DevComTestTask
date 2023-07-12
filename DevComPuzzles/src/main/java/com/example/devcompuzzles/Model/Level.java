package com.example.devcompuzzles.Model;

import java.awt.image.BufferedImage;

public class Level {

    private Difficulty difficulty;

    private String img = null;
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        switch (difficulty){
            case LOW -> img="image1.png";
        }


    }
}
