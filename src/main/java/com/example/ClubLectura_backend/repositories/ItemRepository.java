package com.example.ClubLectura_backend.repositories;

import com.example.ClubLectura_backend.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
}
