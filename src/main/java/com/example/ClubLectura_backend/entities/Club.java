package com.example.ClubLectura_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private AppUser creator;

    private String name;
    private String type;
    private Date creationDate;

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    private List<SelectedItem> selectedItems;

    @OneToMany(mappedBy = "club")
    private List<ClubMembership> memberships;



}
