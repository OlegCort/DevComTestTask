package com.example.devcompuzzles.Controller;

import com.example.devcompuzzles.Algorithm.Puzzle;
import com.example.devcompuzzles.Algorithm.PuzzleController;
import com.example.devcompuzzles.Model.Pazzle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/playground")
public class FieldController {
    int rows;
    int columns;
    static Pazzle[][] field;
    List<Pazzle> pazzles  =new ArrayList<>();


    @GetMapping("/{k}")
    public String get(@PathVariable("k")int k, Model model) throws IOException {
        model.addAttribute("x", k/PazzlesController.getRows(k));
        model.addAttribute("y", PazzlesController.getRows(k));
        model.addAttribute("num", k);

        columns = k/PazzlesController.getRows(k);
        rows = PazzlesController.getRows(k);

        if(field==null) {
            field = new Pazzle[rows][columns];

            Random rand = new Random();
            int upperbound = k;
            boolean rotated1;

            while(pazzles.size()!=k){
                Pazzle pazzle = PazzlesController.pazzles.get(rand.nextInt(upperbound));
                rotated1 = rand.nextBoolean();
                pazzle.setRotated(rotated1);
                if(!pazzles.contains(pazzle))
                    pazzles.add(pazzle);
            }
        }
        model.addAttribute("pazzles", pazzles);
        model.addAttribute("rotated", field);
        model.addAttribute("status", checkStatus());

        return "mainWindow";
    }

    @GetMapping("/setElect/{pazzleNumber}")
    public String elect(@PathVariable("pazzleNumber") int number){

        PazzlesController.setElect(number);
        return "redirect:/playground/" + rows*columns;
    }

    @GetMapping("/add/{row}/{column}")
    public String add(@PathVariable("row") int row, @PathVariable("column")int column){
        Pazzle pazzle = PazzlesController.pazzles.stream().filter(Pazzle::getElect).findFirst().orElse(null);
        if(pazzle==null) return "redirect:/playground/" + rows*columns;
        pazzle.setUsed(true);
        field[row][column] = pazzle;
        pazzle.setElect(false);

        return "redirect:/playground/" + rows*columns;
    }

    @GetMapping("/remove/{row}/{column}")
    public String remove(@PathVariable("row") int row, @PathVariable("column")int column){

        field[row][column].setUsed(false);
        field[row][column] = null;

        return "redirect:/playground/" + rows*columns;
    }

    private String checkStatus(){
        int counter = 1;
        for(int i = 0; i<rows; ++i){
            for(int j = 0; j<columns; ++j){
                if(field[i][j]==null)
                {
                    return "";
                }
            }}

        for(int i = 0; i<rows; ++i){
            for(int j = 0; j<columns; ++j){
                int finalI = i;
                int finalJ = j;
                if(field[finalI][finalJ].getNumber()!=counter++) {
                    return "Incorrect";
                }
            }
        }
        if(PazzlesController.pazzles.stream().filter(Pazzle::isRotated).findFirst().orElse(null)!=null){
            return "Incorrect";
        }
        return "Correct";
    }

    @GetMapping("/rotate")
    public String rotate() throws IOException {
        PazzlesController.rotatePazzle();
        return "redirect:/playground/" + rows*columns;
    }

    @GetMapping("/finish")
    public String finish(){
        PuzzleController puzzleController = new PuzzleController();
        for(Pazzle pazzle: pazzles){
            puzzleController.addPuzzle(pazzle.getImage(), pazzle.getNumber());
        }
        try {
            puzzleController.buildPuzzle();
        }
        catch (Exception e){
            System.out.println("Catched exception: " + e.getMessage());
            return "redirect:/playground/" + rows*columns;
        }
        int[][] field1 = puzzleController.getField();
        System.out.println(field.length);
        System.out.println(field[0].length);
        for(int i = 0; i< field.length; ++i){
            for(int j = 0; j<field[0].length; ++j){
                Pazzle pazzle = PazzlesController.getByNum(field1[i][j]);
                field[i][j] = pazzle;
                pazzle.setUsed(true);
                pazzle.setElect(false);
                pazzle.setRotated(false);
            }
        }
        return "redirect:/playground/" + rows*columns;

    }


}
