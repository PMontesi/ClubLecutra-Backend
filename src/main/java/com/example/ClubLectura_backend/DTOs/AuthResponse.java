package com.example.ClubLectura_backend.DTOs;

import com.example.ClubLectura_backend.entities.Club;
import com.example.ClubLectura_backend.entities.ClubMembership;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class AuthResponse {
    private String token;
    private String username;
    private Set<String> roles; //Por ahora no hay
    private long id;
    private String name;
    //private List<Club> clubsCreated; //Quizña haya que cambiarlo por una lista de ClubMenuDTO

    public AuthResponse(String token, String username, Set<String> roles, long id, String name) {
        this.token = token;
        this.username = username;
        this.roles = roles;
        this.id = id;
        this.name = name;
        //this.clubsCreated = clubsCreated;
    }
}
