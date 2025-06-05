package com.example.ClubLectura_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
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
    private Date releaseDate;
    private long idApi;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<SelectedItem> selectedItems;
}
