package com.cartwheel.galaxy.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.ManagedBean;

@RestController
@RequestMapping("/star-galaxy")
public class MainController {

    @GetMapping("/home")
    public ResponseEntity<?> home(){
        String s ="hello";
        return ResponseEntity.ok().build();
    }

}
