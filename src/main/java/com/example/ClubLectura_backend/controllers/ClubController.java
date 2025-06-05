package com.example.ClubLectura_backend.controllers;

import com.example.ClubLectura_backend.entities.Club;
import com.example.ClubLectura_backend.services.impl.ClubServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    //Attributes
    @Autowired
    ClubServiceImpl clubService;

    //CRUD endpoints
    @PostMapping
    public ResponseEntity<Club> create(@RequestBody Club club) {
        clubService.save(club);
        return ResponseEntity.status(HttpStatus.CREATED).body(club);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getById(@PathVariable long id) {
        return  clubService.findById(id)
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Club> delete(@PathVariable long id) {
        clubService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
