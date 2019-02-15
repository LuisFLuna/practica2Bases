package com.example.demo.controller;

import com.example.demo.model.PlantInventoryEntry;
import com.example.demo.model.PlantInventoryEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlantInventoryEntryController {
    
    @Autowired
    PlantInventoryEntryRepository repository;

    @GetMapping("/plants")
    public String list(Model model){
        model.addAttribute("plants", repository.findAll());
        return "plants/list";
    }

    @GetMapping(value="/plants/form")
    public String form(Model model){
        model.addAttribute("plant", new PlantInventoryEntry());
        return "plants/create";
    }

    @PostMapping(value="/plants")
    public String create(PlantInventoryEntry plant){
        repository.save(plant);
        return "redirect:/plants";
    }
}