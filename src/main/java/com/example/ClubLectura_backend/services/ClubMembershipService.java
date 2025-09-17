package com.example.ClubLectura_backend.services;

import com.example.ClubLectura_backend.entities.AppUser;
import com.example.ClubLectura_backend.entities.Club;
import com.example.ClubLectura_backend.entities.ClubMembership;

import java.util.List;
import java.util.Optional;

public interface ClubMembershipService {

    Optional<ClubMembership> findById(long id);
    List<ClubMembership> findAll();
    void save(ClubMembership clubMembership);
    void delete(long id);
    void delete(ClubMembership clubMembership);
    long countByClub_Id(long clubId);
    Optional<ClubMembership> findByAppUser_Id(long userId);
    List<ClubMembership> findAllByClub_Id(long clubId);
    Optional<ClubMembership> findByAppUser_IdAndClub_Id(long userId, long clubId);
    ClubMembership createMember(AppUser user, Club club, boolean isAdmin);
    ClubMembership getReferenceById(long clubMembershipId);

}
