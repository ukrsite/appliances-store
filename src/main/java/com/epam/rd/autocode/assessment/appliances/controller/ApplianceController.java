package com.epam.rd.autocode.assessment.appliances.controller;

import com.epam.rd.autocode.assessment.appliances.service.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplianceController {

    @Autowired
    ApplianceService applianceService;

    @RequestMapping("/appliances")
    public String showProducts(Model model) {
        model.addAttribute("appliances", applianceService.getAllAppliances());
        return "appliance/appliances";
    }
}

