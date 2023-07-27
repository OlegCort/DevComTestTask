package com.example.devcompuzzles.Controller;


import com.example.devcompuzzles.Model.Difficulty;
import com.example.devcompuzzles.Model.Level;
import com.example.devcompuzzles.Model.Pazzle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;


@Controller
public class HomeController {


    FieldController fieldController;

    @Autowired
    public HomeController(FieldController fieldController){
        this.fieldController = fieldController;
    }

    @GetMapping({"/","/home"})
    public String homePage(Model model){
        model.addAttribute("level", new Level());
        return "home";
    }

    @PostMapping({"/","/home/"})
    public String homePage(@ModelAttribute("level") Level level, Model model) throws IOException {
        FieldController.field = null;
        File file;
        for(int i = 1; i<=20; ++i){
            file = new File("src/main/resources/static/Images/Pieces/piece"+i+  ".png");
            file.delete();

        }
        int k;
        if(level.getDifficulty()==Difficulty.LOW)
            k = 9;
        else if (level.getDifficulty()==Difficulty.MEDIUM)
            k = 12;
        else
            k = 20;
        PazzlesController.pazzles = PazzlesController.splitImage("/Users/olegmisialo/Desktop/DevComTask/DevComTestTask/DevComPuzzles/src/main/resources/static/Images/", "picture.png", k);


        return "redirect:/playground/"+k;
    }

}
