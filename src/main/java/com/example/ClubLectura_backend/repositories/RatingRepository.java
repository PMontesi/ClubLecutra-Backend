package com.example.ClubLectura_backend.repositories;

import com.example.ClubLectura_backend.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    public List<Rating> findBySelectedItem_Id(long selectedItemId);
    public List<Rating> findByClubMember_Id(long clubMemberId);
    public Rating findByClubMember_IdAndSelectedItem_Id(long clubMemberId, long selectedItemId);
    @Query("SELECT r FROM " +
            "Rating r " +
            "JOIN r.selectedItem se " +
            "JOIN r.clubMember cm " +
            "JOIN cm.appUser a " +
            "WHERE a.id = :userid AND se.id = :selectedItemId")
    public Rating findByAppUserIdAndSelectedItemId(@Param("userid") long appUserId, @Param("selectedItemId")long selectedItemId);
}
