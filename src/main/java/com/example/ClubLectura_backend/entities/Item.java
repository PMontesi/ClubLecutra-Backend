package com.example.ClubLectura_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String type;
    private String location;
    private String author;
    private LocalDate releaseDate;
    private long idApi;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<SelectedItem> selectedItems;
}
