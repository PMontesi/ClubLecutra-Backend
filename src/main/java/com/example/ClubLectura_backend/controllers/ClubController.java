package com.example.ClubLectura_backend.controllers;

import com.example.ClubLectura_backend.DTOs.ClubDTO;
import com.example.ClubLectura_backend.entities.Club;
import com.example.ClubLectura_backend.services.impl.ClubServiceImpl;
import com.example.ClubLectura_backend.services.manager.ClubManagerService;
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
    @Autowired
    ClubManagerService clubManagerService;

    //CRUD endpoints
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClubDTO clubDto) {
        try {
            Club newClub = clubManagerService.createClub(clubDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newClub);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error" + e.getMessage());
        }
        //Todo añadir en un futuro una clase que sea ClubResponseDTO que devuelve solo los datos seleccionados
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getById(@PathVariable long id) {
        return  clubService.findById(id)
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        if(clubManagerService.deleteClub(id)) {
            return ResponseEntity.ok("Club deleted");
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Can't delete a club with more than one member");
        }
    }



}
