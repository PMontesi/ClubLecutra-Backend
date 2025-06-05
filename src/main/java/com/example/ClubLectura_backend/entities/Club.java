package com.example.ClubLectura_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    @JsonBackReference
    private AppUser creator;

    private String name;
    private String type;
    private LocalDate creationDate;

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    private List<SelectedItem> selectedItems;

    @OneToMany(mappedBy = "club")
    private List<ClubMembership> memberships;



}
