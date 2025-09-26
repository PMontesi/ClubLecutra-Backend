package com.example.ClubLectura_backend.services.impl;

import com.example.ClubLectura_backend.DTOs.RatingDTO;
import com.example.ClubLectura_backend.entities.Rating;
import com.example.ClubLectura_backend.repositories.RatingRepository;
import com.example.ClubLectura_backend.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    //Attributes
    @Autowired
    RatingRepository ratingRepository;

    //CRUD Methods
    @Override
    public Optional<Rating> findById(long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public void save(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public void delete(long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public void delete(Rating rating) {
        ratingRepository.delete(rating);
    }

    @Override
    public List<Rating> findBySelectedItem_Id(long selectedItemId) {
        return ratingRepository.findBySelectedItem_Id(selectedItemId);
    }

    @Override
    public List<Rating> findByClubMember_Id(long clubMemberId) {
        return ratingRepository.findByClubMember_Id(clubMemberId);
    }

    @Override
    public Optional<Rating> findByClubMember_IdAndSelectedItem_Id(long clubMemberId, long selectedItemId) {
        return ratingRepository.findByClubMember_IdAndSelectedItem_Id(clubMemberId, selectedItemId);
    }




    //Logic Methods

    /*
    todo métodos de Item a añadir

    Si en un futuro me da por añadir comentarios (como críticas o algo)
    habrá que ampliar el modelo y tal

    -Crear una valoración
        -Lo que implica involucrar al usuario de alguna forma
        -Y al item seleccionado también

     */
}
