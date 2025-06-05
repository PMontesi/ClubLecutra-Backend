package com.example.ClubLectura_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ClubMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Club club;

    private boolean owner;
    private LocalDate unionDate;

}
