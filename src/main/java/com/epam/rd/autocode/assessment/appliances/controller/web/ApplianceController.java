package com.epam.rd.autocode.assessment.appliances.controller.web;

import com.epam.rd.autocode.assessment.appliances.aspect.Loggable;
import com.epam.rd.autocode.assessment.appliances.model.Appliance;
import com.epam.rd.autocode.assessment.appliances.service.ApplianceService;
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
@RequestMapping("/appliances")
public class ApplianceController {
    private final ApplianceService applianceService;

    public ApplianceController(ApplianceService applianceService) {
        this.applianceService = applianceService;
    }

    @Loggable
    @GetMapping
    public String getAllAppliances(Model model, @PageableDefault(size = 5, sort = "id") Pageable pageable) {
        model.addAttribute("appliances", applianceService.getAllAppliances(pageable));

        return "appliance/appliances";
    }

    @Loggable
    @GetMapping("/add")
    public String addApplianceForm(Model model) {
        fillModelAttributes(model, new Appliance());

        return "appliance/newAppliance";
    }

    @Loggable
    @PostMapping({"/add-appliance", "/edit-appliance"})
    public String addAppliance(@ModelAttribute @Valid Appliance appliance) {
        applianceService.saveAppliance(appliance);
        return "redirect:/appliances";
    }

    @Loggable
    @GetMapping("/edit")
    public String editApplianceForm(@RequestParam("id") Long id, Model model) {
        Appliance appliance = applianceService.getApplianceById(id);
        fillModelAttributes(model, appliance);

        return "appliance/editAppliance";
    }

    @Loggable
    @DeleteMapping("/delete")
    public String deleteAppliance(@RequestParam("id") Long id, HttpServletRequest request) {
        applianceService.deleteApplianceById(id);

        return "redirect:" + request.getHeader("Referer");
    }

    private void fillModelAttributes(Model model, Appliance appliance) {
        model.addAttribute("appliance", appliance);
        model.addAttribute("categories", applianceService.getCategories());
        model.addAttribute("manufacturers", applianceService.getManufacturers());
        model.addAttribute("powerTypes", applianceService.getPowerTypes());
    }
}

