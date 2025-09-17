package com.example.ClubLectura_backend.services;

import com.example.ClubLectura_backend.entities.SelectedItem;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SelectedItemService {

    Optional<SelectedItem> findById(long id);
    List<SelectedItem> findAll();
    void save(SelectedItem selectedItem);
    void delete(long id);
    void delete(SelectedItem selectedItem);
    Optional<SelectedItem> findActiveByClubId(long clubId);
    SelectedItem getReferenceById(long selectedItemId);

}
