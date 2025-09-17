package com.example.ClubLectura_backend.services;

import com.example.ClubLectura_backend.entities.Item;
import com.example.ClubLectura_backend.entities.SelectedItem;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Optional<Item> findById(long id);
    List<Item> findAll();
    void save(Item item);
    void delete(long id);
    void delete(Item item);
    Item getReferenceById(long itemId);

    //Item findBySelectedItem(SelectedItem selectedItem);
}
