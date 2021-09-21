package com.example.sboot.controllers;

import com.example.sboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("")
public class FirstController {

    private final UserService userService;

    public FirstController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/{username}")
//    public String show(@PathVariable("username") String username, Model model, Principal principal) {
//        model.addAttribute("userP", userService.findByUsername(principal.getName()));
//        model.addAttribute("user", userService.findByUsername(username));
//        return "admin/show";
//    }

    @GetMapping
    public String hello() {
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }



}










































