package com.example.ClubLectura_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
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

    private Date registerDate;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Club> groupsCreated;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
    private List<SelectedItem> selectedItems;

    @OneToMany(mappedBy = "appUser")
    private List<ClubMembership> memberships;



}
