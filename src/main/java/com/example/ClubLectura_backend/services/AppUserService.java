package com.example.ClubLectura_backend.services;

import com.example.ClubLectura_backend.entities.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    Optional<AppUser> findById(long id);
    List<AppUser> findAll();
    void save(AppUser appUser);
    void delete(long id);
    void delete(AppUser appUser);
    Optional<AppUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
