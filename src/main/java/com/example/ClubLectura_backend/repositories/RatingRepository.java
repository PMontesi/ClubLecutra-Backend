package com.example.ClubLectura_backend.repositories;

import com.example.ClubLectura_backend.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findBySelectedItem_Id(long selectedItemId);
    List<Rating> findByClubMember_Id(long clubMemberId);
    Optional<Rating> findByClubMember_IdAndSelectedItem_Id(long clubMemberId, long selectedItemId);

    @Query("SELECT r FROM " +
            "Rating r " +
            "JOIN r.selectedItem se " +
            "JOIN r.clubMember cm " +
            "WHERE cm.id = :clubMemberId AND se.id = :selectedItemId")
    Optional<Rating> findByClubMemberIdAndSelectedItemId(@Param("clubMemberId") long clubMemberId, @Param("selectedItemId")long selectedItemId);



}
