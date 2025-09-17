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
    public String name;
    public String location;
    public String Author;
    public LocalDate releaseDate;
    //public String synopsis;
    //public String image;

    // From selected item entity
    public LocalDate startDate;
    public LocalDate endDate;
    public int rating;
}
