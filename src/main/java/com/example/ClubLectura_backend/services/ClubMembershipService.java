package com.example.ClubLectura_backend.services;

import com.example.ClubLectura_backend.entities.ClubMembership;

import java.util.List;
import java.util.Optional;

public interface ClubMembershipService {

    Optional<ClubMembership> findById(long id);
    List<ClubMembership> findAll();
    void save(ClubMembership clubMembership);
    void delete(long id);
    void delete(ClubMembership clubMembership);

}
