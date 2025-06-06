package com.example.ClubLectura_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Rating {

    //Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "selected_item_id", nullable = false)
    private SelectedItem selectedItem;

    @ManyToOne
    @JoinColumn(name = "club_member_id", nullable = false)
    private ClubMembership clubMember;

    @Column(nullable = false)
    private int score;

    private LocalDate date;


}
