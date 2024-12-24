package com.appliances.controller;

import com.appliances.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/manufacturers")
    public String showManufacturers(Model model) {
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        return "manufacture/manufacturers";
    }
}
