package com.example.ClubLectura_backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EndDateRequest {

    private long selectedItemId;
    private LocalDate newEndDate;
}
