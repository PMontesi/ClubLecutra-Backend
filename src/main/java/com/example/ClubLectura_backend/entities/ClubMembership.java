package com.example.ClubLectura_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class ClubMembership {

    //Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonBackReference(value = "memberships")
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    @ManyToOne
    @JsonBackReference(value = "club_id")
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    private boolean admin;
    private LocalDate unionDate;

    //Relations
    @OneToMany(mappedBy = "clubMember")
    private List<Rating> ratings;


}
