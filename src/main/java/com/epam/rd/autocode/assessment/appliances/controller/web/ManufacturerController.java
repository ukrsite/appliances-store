package com.epam.rd.autocode.assessment.appliances.controller.web;

import com.epam.rd.autocode.assessment.appliances.aspect.Loggable;
import com.epam.rd.autocode.assessment.appliances.model.Manufacturer;
import com.epam.rd.autocode.assessment.appliances.service.ManufacturerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Loggable
    @GetMapping
    public String getAllManufacturers(@PageableDefault(size = 5, sort = "id") Pageable pageable, Model model) {
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers(pageable));

        return "manufacture/manufacturers";
    }

    @Loggable
    @GetMapping("/add")
    public String addManufacturerForm(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());

        return "manufacture/newManufacturer";
    }

    @Loggable
    @GetMapping("/edit")
    public String editManufacturerForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("manufacturer", manufacturerService.getManufacturerById(id));

        return "manufacture/editManufacturer";
    }

    @Loggable
    @PostMapping({"/add-manufacturer", "/edit-manufacturer"})
    public String addManufacturer(@ModelAttribute @Valid Manufacturer manufacturer) {
        manufacturerService.saveManufacturer(manufacturer);

        return "redirect:/manufacturers";
    }

    @Loggable
    @DeleteMapping("/delete")
    public String deleteManufacturer(@RequestParam("id") Long id, HttpServletRequest request) {
        manufacturerService.deleteManufacturerById(id);

        return "redirect:" + request.getHeader("Referer");
    }
}