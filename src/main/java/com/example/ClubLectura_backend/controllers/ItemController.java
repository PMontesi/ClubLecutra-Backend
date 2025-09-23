package com.example.ClubLectura_backend.controllers;

import com.example.ClubLectura_backend.DTOs.CardItemDTO;
import com.example.ClubLectura_backend.DTOs.RatingDTO;
import com.example.ClubLectura_backend.DTOs.SelectedItemNewDTO;
import com.example.ClubLectura_backend.services.impl.ItemServiceImpl;
import com.example.ClubLectura_backend.services.impl.SelectedItemServiceImpl;
import com.example.ClubLectura_backend.services.manager.ItemManagerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    ItemManagerService itemManagerService;
    @Autowired
    ItemServiceImpl itemService;
    @Autowired
    SelectedItemServiceImpl selectedItemService;


    @GetMapping("/actualClubItem")
    public ResponseEntity<CardItemDTO> getActualClubItem(@RequestParam long selectedItemId, @RequestParam long clubMemberId) {
        try {
            CardItemDTO dto = itemManagerService.createCardItemDTO(selectedItemId, clubMemberId);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CardItemDTO> createSelectedItem(@RequestBody SelectedItemNewDTO newSelectedItem) {
        if (newSelectedItem.getOldSelectedItemId() != 0) {
            selectedItemService.isActiveToFalse(newSelectedItem.getOldSelectedItemId());
        }

        //Este trycatch hay que cambiarlo una miaja
        try {
            CardItemDTO dto = itemManagerService.createSelectedItem(newSelectedItem);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/endDate")
    public ResponseEntity<?> changeSelectedItemEndDate(@RequestParam long selectedItemId, @RequestParam LocalDate newEndDate) {
       try {
           LocalDate updatedEndate = selectedItemService.changeEndDate(selectedItemId, newEndDate);
           return ResponseEntity.ok(updatedEndate);
       } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @PutMapping("/cancel")
    public ResponseEntity<String> cancelSelectedItem(@RequestParam long selectedItemId) {
        selectedItemService.isActiveToFalse(selectedItemId);
        return ResponseEntity.ok("Selected Item " + selectedItemId + " deactivated");
    }

    @PostMapping("/rate")
    public ResponseEntity<?> rateSelectedItem(@RequestBody RatingDTO ratingDTO) {
        itemManagerService.rateSelectedItem(ratingDTO);
        return ResponseEntity.ok("Selected Item rated successfully");
    }




}
