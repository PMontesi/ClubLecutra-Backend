package com.example.ClubLectura_backend.services;

import com.example.ClubLectura_backend.DTOs.ClubMenuDTO;
import com.example.ClubLectura_backend.entities.Club;

import java.util.List;
import java.util.Optional;

public interface ClubService {

    Optional<Club> findById(long id);
    List<Club> findAll();
    Club save(Club club);
    void delete(long id);
    void delete(Club club);
    List<Object[]> findClubsForUser(long userId);
    Club getReferenceById(long clubId);
}
