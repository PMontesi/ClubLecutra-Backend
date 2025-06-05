package com.example.ClubLectura_backend.controllers;

import com.example.ClubLectura_backend.DTOs.ClubDTO;
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
    public ResponseEntity<Club> create(@RequestBody ClubDTO clubDto) {
        Club newClub = clubService.createClub(clubDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClub);

        //Todo añadir en un futuro una clase que sea ClubResponseDTO que devuelve solo los datos seleccionados
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
