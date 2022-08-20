package com.testspring.demorest.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class HelloWorldController {
    @RequestMapping(value = "/greeting")
    public String helloWorldController(@RequestParam(
            name="name", required = false, defaultValue = "World") String name, Model model){
            model.addAttribute("name", name);
            return "greeting";
    }
}
