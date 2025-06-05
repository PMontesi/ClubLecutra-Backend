package com.example.ClubLectura_backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDate registerDate;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Club> groupsCreated;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
    private List<SelectedItem> selectedItems;

    @OneToMany(mappedBy = "appUser")
    private List<ClubMembership> memberships;



}
