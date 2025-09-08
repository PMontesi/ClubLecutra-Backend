package com.example.ClubLectura_backend.controllers;

import com.example.ClubLectura_backend.entities.AppUser;
import com.example.ClubLectura_backend.services.impl.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    //Attributes
    @Autowired
    AppUserServiceImpl appUserService;

    //CRUD endpoints
    @PostMapping
    public ResponseEntity<AppUser> create(@RequestBody AppUser user) {
        appUserService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppUser> delete(@PathVariable long id) {
        appUserService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getById(@PathVariable long id) {
        return  appUserService.findById(id)
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    //Hacer el update más adelante, que ahora no me hace falta

    //Test endpoint
    @GetMapping("/mensaje")
    public String mensaje() {
        return "Hola el sistema funciona";
    }

}
