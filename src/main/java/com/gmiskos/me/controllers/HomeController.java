package com.gmiskos.me.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/home")
public class HomeController {

    @GetMapping(path = "{message}")
    public String getStudent(@PathVariable("message") String message) {
        return "Hello "+message;
    }
}
