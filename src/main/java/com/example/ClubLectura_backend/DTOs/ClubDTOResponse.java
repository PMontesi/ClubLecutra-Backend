package com.example.ClubLectura_backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubDTOResponse {
    private long clubId;
    private long userClubMemberId;
    private String name;
}
