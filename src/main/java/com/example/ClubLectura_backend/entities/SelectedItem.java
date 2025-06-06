package com.example.ClubLectura_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class SelectedItem {

    //Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "club_member_id", nullable = false)
    private ClubMembership clubMember;

    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;

    //Relations
    @OneToMany(mappedBy = "selectedItem")
    private List<Rating> ratings;

}
