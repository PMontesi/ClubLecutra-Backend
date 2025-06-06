package com.example.ClubLectura_backend.repositories;

import com.example.ClubLectura_backend.entities.ClubMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubMembershipRepository extends JpaRepository<ClubMembership, Long> {

    //Method to get the number of rows whose groupId are the same
    long countByClub_Id(long clubId);

    //Method to find a club member by the user id
    Optional<ClubMembership> findByAppUser_Id(long userId);
    List<ClubMembership> findAllByClub_Id(long clubId);
    Optional<ClubMembership> findByAppUser_IdAndClub_Id(long userId, long clubId);
}
