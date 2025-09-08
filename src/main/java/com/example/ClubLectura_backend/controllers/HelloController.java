package com.example.ClubLectura_backend.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hola, ¡has accedido con JWT!";
    }
}