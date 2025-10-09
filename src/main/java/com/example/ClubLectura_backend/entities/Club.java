package com.example.ClubLectura_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    @JsonBackReference(value = "clubsCreated")
    private AppUser creator;

    private String name;
    private String type;
    private LocalDate creationDate = LocalDate.now();

    @OneToMany(mappedBy = "club", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "club_id")
    private List<ClubMembership> memberships = new ArrayList<>();

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<SelectedItem> selectedItems;

    public Club(AppUser creator, String name, String type) {
        this.creator = creator;
        this.name = name;
        this.type = type;
    }

    public void addClubMembership(ClubMembership clubMembership) {
        this.memberships.add(clubMembership);
    }
}

