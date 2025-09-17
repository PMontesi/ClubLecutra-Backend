package com.example.ClubLectura_backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Item {

    //Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String type;
    private String location;
    private String author;
    private LocalDate releaseDate;
    private long apiId;

    //Relations
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<SelectedItem> selectedItems;

    public Item(String name, String type, String location, String author, LocalDate releaseDate) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.author = author;
        this.releaseDate = releaseDate;
    }
}
