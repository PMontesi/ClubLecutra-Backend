package com.example.ClubLectura_backend.services.impl;

import com.example.ClubLectura_backend.DTOs.EndDateRequest;
import com.example.ClubLectura_backend.entities.SelectedItem;
import com.example.ClubLectura_backend.repositories.SelectedItemRepository;
import com.example.ClubLectura_backend.services.SelectedItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public SelectedItem save(SelectedItem selectedItem) {
        return selectedItemRepository.save(selectedItem);
    }

    @Override
    public void delete(long id) {
        selectedItemRepository.deleteById(id);
    }

    @Override
    public void delete(SelectedItem selectedItem) {
        selectedItemRepository.delete(selectedItem);
    }

    @Override
    public Optional<SelectedItem> findActiveByClubId(long clubId) {
        return selectedItemRepository.findActiveByClubId(clubId);
    }

    @Override
    public SelectedItem getReferenceById(long selectedItemId) {
        return selectedItemRepository.getReferenceById(selectedItemId);
    }


    //Logic Methods

    public void isActiveToFalse(long selectedItemId) {
        SelectedItem se = selectedItemRepository.findById(selectedItemId)
                .orElseThrow(() -> new EntityNotFoundException("No Selected Item found"));
        se.setIsActive(false);
        selectedItemRepository.save(se);
    }

    public LocalDate changeEndDate(EndDateRequest request) throws Exception {
        SelectedItem se = selectedItemRepository.findById(request.getSelectedItemId())
                .orElseThrow(() -> new EntityNotFoundException("No Selected Item found"));

        if(request.getNewEndDate().isBefore(se.getStartDate()) || request.getNewEndDate().isBefore(LocalDate.now())) {
            throw new Exception("New end date can't be before the start date or today's date");
        }

        se.setEndDate(request.getNewEndDate());
        selectedItemRepository.save(se);

        return se.getEndDate();
    }

    /*
    todo Métodos de SelectedItem a añadir

    -Seleccionar un item
        -Básicamente es la función de crear uno
     */
}
