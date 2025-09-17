package com.example.ClubLectura_backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class SelectedItem {

    //Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    private LocalDate startDate = LocalDate.now();
    private LocalDate endDate;
    private Boolean isActive;

    //Relations
    @OneToMany(mappedBy = "selectedItem")
    private List<Rating> ratings;

    public SelectedItem(Item item, Club club, LocalDate startDate, LocalDate endDate, Boolean isActive) {
        this.item = item;
        this.club = club;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    // Constructor solo para el seeder
    public SelectedItem(Item item, Club club, Boolean isActive) {
        this.item = item;
        this.club = club;
        this.startDate = LocalDate.now();
        this.endDate = this.startDate.plusMonths(1);
        this.isActive = isActive;
    }

}
