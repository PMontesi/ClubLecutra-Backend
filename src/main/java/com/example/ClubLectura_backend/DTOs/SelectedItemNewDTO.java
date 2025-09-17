package com.example.ClubLectura_backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedItemNewDTO {
    private long itemId;
    private LocalDate startDate;
    private LocalDate endDate;
    private long clubId;
    private long oldSelectedItemId;
}
