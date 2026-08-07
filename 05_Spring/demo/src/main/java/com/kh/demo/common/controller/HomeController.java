package com.kh.demo.common.controller;

import com.kh.demo.common.dto.ApodResponse;
import com.kh.demo.common.service.NasaApodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private NasaApodService nasaApodService;

    @GetMapping("/")
    public String home(Model model){

        ApodResponse apod = nasaApodService.getTodayApod();
        model.addAttribute("apod", apod);

        return "home/index";
    }
}
