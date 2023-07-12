package com.example.devcompuzzles.Model;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class Pazzle {

    String path;
    int number;
    BufferedImage image;

    boolean rotated;

    public boolean isRotated() {
        return rotated;
    }

    public void setRotated(boolean rotated) {
        this.rotated = rotated;
    }

    boolean elect;
    boolean used;
    public boolean getElect() {
        return elect;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setElect(boolean elect) {
        this.elect = elect;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object obj){
        if(obj.getClass()!=this.getClass()||obj==null)
            return false;
        return this.number==((Pazzle) obj).number;
    }
}
