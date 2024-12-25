package com.epam.rd.autocode.assessment.appliances.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }
}
