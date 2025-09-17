package com.example.ClubLectura_backend.DTOs;

import com.example.ClubLectura_backend.entities.Club;
import com.example.ClubLectura_backend.entities.ClubMembership;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDTO {

    private long id;

    private String name;

    private String email;

    private List<Club> clubsCreated;

    private List<ClubMembership> memberships;

}
