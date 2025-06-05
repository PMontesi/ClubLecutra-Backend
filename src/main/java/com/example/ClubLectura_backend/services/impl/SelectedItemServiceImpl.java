package com.example.ClubLectura_backend.services.impl;

import com.example.ClubLectura_backend.entities.SelectedItem;
import com.example.ClubLectura_backend.repositories.SelectedItemRepository;
import com.example.ClubLectura_backend.services.SelectedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelectedItemServiceImpl implements SelectedItemService {

    //Attributes
    @Autowired
    SelectedItemRepository selectedItemRepository;

    //CRUD Methods
    @Override
    public Optional<SelectedItem> findById(long id) {
        return selectedItemRepository.findById(id);
    }

    @Override
    public List<SelectedItem> findAll() {
        return selectedItemRepository.findAll();
    }

    @Override
    public void save(SelectedItem selectedItem) {
        selectedItemRepository.save(selectedItem);
    }

    @Override
    public void delete(long id) {
        selectedItemRepository.deleteById(id);
    }

    @Override
    public void delete(SelectedItem selectedItem) {
        selectedItemRepository.delete(selectedItem);
    }

    //Logic Methods

    /*
    todo Métodos de SelectedItem a añadir

    -Seleccionar un item
        -Básicamente es la función de crear uno
     */
}
