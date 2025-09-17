package com.example.ClubLectura_backend.repositories;

import com.example.ClubLectura_backend.entities.SelectedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SelectedItemRepository extends JpaRepository<SelectedItem, Long> {

    @Query("SELECT s FROM SelectedItem s WHERE s.club.id = :clubId AND s.isActive = true")
    Optional<SelectedItem> findActiveByClubId(@Param("clubId") long clubId);

}
