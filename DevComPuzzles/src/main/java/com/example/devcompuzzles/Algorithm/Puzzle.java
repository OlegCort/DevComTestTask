package com.example.devcompuzzles.Algorithm;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Puzzle {


    private final BufferedImage puzzle;
    private final int[][] upperSide;
    private final int[][] lowerSide;
    private final int[][] rightSide;
    private final int[][] leftSide;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public Puzzle(BufferedImage puzzle){
        this.puzzle = puzzle;
        int height = puzzle.getHeight();
        int width = puzzle.getWidth();

        upperSide = new int[width/2][3];
        lowerSide = new int[width/2][3];
        rightSide = new int[height/2][3];
        leftSide = new int[height/2][3];

        for(int i = 0; i<width/2; ++i){
            upperSide[i][0] = (new Color(puzzle.getRGB(i*2, 0)).getBlue()
                    + new Color(puzzle.getRGB(i*2+1, 0)).getBlue()
                    )/2;
            upperSide[i][1] = (new Color(puzzle.getRGB(i*2, 0)).getGreen()
                    + new Color(puzzle.getRGB(i*2+1, 0)).getGreen()
            )/2;
            upperSide[i][2] = (new Color(puzzle.getRGB(i*2, 0)).getRed()
                    + new Color(puzzle.getRGB(i*2+1, 0)).getRed()
            )/2;

            lowerSide[i][0] = ( new Color(puzzle.getRGB(i*2, height-1) ).getBlue()
                    + new Color(puzzle.getRGB(i*2+1, height-1)).getBlue()
                    )/2;
            lowerSide[i][1] = ( new Color(puzzle.getRGB(i*2, height-1) ).getGreen()
                    + new Color(puzzle.getRGB(i*2+1, height-1)).getGreen()
            )/2;
            lowerSide[i][2] = ( new Color(puzzle.getRGB(i*2, height-1) ).getRed()
                    + new Color(puzzle.getRGB(i*2+1, height-1)).getRed()
            )/2;

        }

        for(int i = 0; i<height/2; ++i){
            leftSide[i][0] = (new Color(puzzle.getRGB(0, i*2)).getBlue()
                    + new Color(puzzle.getRGB(0, i*2 + 1)).getBlue()
                    )/2;
            leftSide[i][1] = (new Color(puzzle.getRGB(0, i*2)).getGreen()
                    + new Color(puzzle.getRGB(0, i*2 + 1)).getGreen()
            )/2;
            leftSide[i][2] = (new Color(puzzle.getRGB(0, i*2)).getRed()
                    + new Color(puzzle.getRGB(0, i*2 + 1)).getRed()
            )/2;


            rightSide[i][0] = ( new Color(puzzle.getRGB(width-1, i*2)).getBlue()
                    + new Color(puzzle.getRGB(width-1, i*2 + 1)).getBlue()
                    )/2;
            rightSide[i][1] = ( new Color(puzzle.getRGB(width-1, i*2)).getGreen()
                    + new Color(puzzle.getRGB(width-1, i*2 + 1)).getGreen()
            )/2;
            rightSide[i][2] = ( new Color(puzzle.getRGB(width-1, i*2)).getRed()
                    + new Color(puzzle.getRGB(width-1, i*2 + 1)).getRed()
            )/2;

        }
    }

    public BufferedImage getPuzzle() {
        return puzzle;
    }

    public int[][] getUpperSide() {
        return upperSide;
    }

    public int[][] getLowerSide() {
        return lowerSide;
    }

    public int[][] getRightSide() {
        return rightSide;
    }

    public int[][] getLeftSide() {
        return leftSide;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj){
        if(obj==null||obj.getClass()!=Puzzle.class) return false;
        return this.id==((Puzzle)obj).getId();
    }

    @Override
    public String toString(){
        return "Id: " + id;
    }
}
