package com.epam.rd.autocode.assessment.appliances.controller.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

@RestController
@RequestMapping("/api/localization")
public class RestLocalizationController {
    private final LocaleResolver localeResolver;

    public RestLocalizationController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @PostMapping("")
    public ResponseEntity<String> changeLanguage(@RequestParam("lang") String lang,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) {
        Locale locale = new Locale(lang);
        localeResolver.setLocale(request, response, locale);
        return ResponseEntity.ok("Language changed to " + lang);
    }
}
