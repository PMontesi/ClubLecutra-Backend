package com.example.ClubLectura_backend.DTOs;

import com.example.ClubLectura_backend.entities.Club;
import com.example.ClubLectura_backend.entities.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    // Diferents id
    private long itemId;
    private long selectedItemId;
    private long apiId; //Esto por ahora no sirve para nada

    // Item information
    private String name;
    private String type;
    private String location;
    private String author;

    // Related club information
    private LocalDate releaseDate;
    private Club club;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private List<Rating> ratings;

}
