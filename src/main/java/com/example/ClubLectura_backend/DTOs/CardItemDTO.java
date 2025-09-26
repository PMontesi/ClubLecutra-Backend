package com.example.ClubLectura_backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardItemDTO {

    // From item entity
    private String name;
    private String location;
    private String Author;
    private LocalDate releaseDate;
    //public String synopsis;
    //public String image;

    // From selected item entity
    private LocalDate startDate;
    private LocalDate endDate;
    private int rating;
    private long selectedItemId;

}
