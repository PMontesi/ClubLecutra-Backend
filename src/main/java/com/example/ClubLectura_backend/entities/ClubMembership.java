package com.example.ClubLectura_backend.entities;

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
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    private boolean admin;
    private LocalDate unionDate;

    //Relations
    @OneToMany(mappedBy = "clubMember", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<SelectedItem> selectedItems;

    @OneToMany(mappedBy = "clubMember")
    private List<Rating> ratings;


}
