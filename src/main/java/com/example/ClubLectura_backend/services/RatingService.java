package com.example.ClubLectura_backend.services;

import com.example.ClubLectura_backend.entities.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingService {

    Optional<Rating> findById(long id);
    List<Rating> findAll();
    void save(Rating rating);
    void delete(long id);
    void delete(Rating rating);

}
