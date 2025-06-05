package com.example.ClubLectura_backend.repositories;

import com.example.ClubLectura_backend.entities.ClubMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubMembershipRepository extends JpaRepository<ClubMembership, Long> {
}
