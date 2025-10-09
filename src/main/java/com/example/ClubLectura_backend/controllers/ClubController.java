package com.example.ClubLectura_backend.controllers;

import com.example.ClubLectura_backend.DTOs.ClubDTO;
import com.example.ClubLectura_backend.DTOs.ClubDTOResponse;
import com.example.ClubLectura_backend.DTOs.ClubMenuDTO;
import com.example.ClubLectura_backend.entities.Club;
import com.example.ClubLectura_backend.services.impl.ClubServiceImpl;
import com.example.ClubLectura_backend.services.manager.ClubManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            ClubDTOResponse dtoResponse = clubManagerService.createClub(clubDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error" + e.getMessage());
        }
        //Todo añadir en un futuro una clase que sea ClubResponseDTO que devuelve solo los datos seleccionados
    }

    // Todo hacer el dto de respuesta
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

    /*
        Devuelve todos los clubs a los que pertenece un usuario
        Para la vista del dashboard nada más hacer login
     */
    @GetMapping("/menu")
    public List<ClubMenuDTO> getClubsForMenu(@RequestParam long userId) {
        return clubService.getMenuDTO(userId);
    }



}
