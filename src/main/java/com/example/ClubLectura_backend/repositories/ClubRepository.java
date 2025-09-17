package com.example.ClubLectura_backend.repositories;

import com.example.ClubLectura_backend.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    // Todo Añadirle la imagen cuando esté
    // Todo Problema serio con los clubs que no tienen ningún item activo pero están ahí
    @Query("SELECT c.id, c.name, si.id, i.name, cm.id " +
            "FROM Club c " +
            "JOIN c.memberships cm " +
            "JOIN cm.appUser a " +
            "LEFT JOIN c.selectedItems si WITH si.isActive = true " +
            "LEFT JOIN si.item i " +
            "WHERE a.id = :userid ")
    List<Object[]> findClubsForUser(@Param("userid") long userId);
}
