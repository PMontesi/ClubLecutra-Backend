package com.example.ClubLectura_backend.controllers;

import com.example.ClubLectura_backend.DTOs.CardItemDTO;
import com.example.ClubLectura_backend.DTOs.EndDateRequest;
import com.example.ClubLectura_backend.DTOs.RatingDTO;
import com.example.ClubLectura_backend.DTOs.SelectedItemNewDTO;
import com.example.ClubLectura_backend.exceptions.InvalidEndDateException;
import com.example.ClubLectura_backend.services.impl.ItemServiceImpl;
import com.example.ClubLectura_backend.services.impl.SelectedItemServiceImpl;
import com.example.ClubLectura_backend.services.manager.ItemManagerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<CardItemDTO> getActualClubItem(@RequestParam long clubId, @RequestParam long clubMemberId) {

        Optional<CardItemDTO> dtoOpt = itemManagerService.createCardItemDTO(clubId, clubMemberId);

        CardItemDTO response = dtoOpt.orElseGet(() -> {
            CardItemDTO emptyDto = new CardItemDTO();
            emptyDto.setRating(-1);
            emptyDto.setActive(false);
            return emptyDto;
        });

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CardItemDTO> createSelectedItem(@RequestBody SelectedItemNewDTO newSelectedItem) {
        if (newSelectedItem.getOldSelectedItemId() != 0) {
            selectedItemService.isActiveToFalse(newSelectedItem.getOldSelectedItemId());
        }

        //Todo Este trycatch hay que cambiarlo una miaja
        try {
            CardItemDTO dto = itemManagerService.createSelectedItem(newSelectedItem);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/endDate")
    public ResponseEntity<?> changeSelectedItemEndDate(@PathVariable("id") long selectedItemId, @RequestBody EndDateRequest request) {
       try {
           LocalDate updatedEndate = selectedItemService.changeEndDate(selectedItemId, request);
           return ResponseEntity.ok(updatedEndate);
       } catch (EntityNotFoundException e) {
           Map<String, String> error = Map.of(
                   "code", "NOT_FOUND",
                   "message", e.getMessage()
           );
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
       } catch (InvalidEndDateException e) {
           Map<String, String> error = Map.of(
                   "code", "INVALID_END_DATE",
                   "message", e.getMessage()
           );
           return ResponseEntity.badRequest().body(error);
       }
    }

    // Todo manejar los errores
    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelSelectedItem(@PathVariable("id") long selectedItemId) {
        selectedItemService.isActiveToFalse(selectedItemId);
        return ResponseEntity.ok("Selected Item " + selectedItemId + " deactivated");
    }

    @PostMapping("/{id}/rate")
    public ResponseEntity<?> rateSelectedItem(@PathVariable("id") long selectedItemId , @RequestBody RatingDTO ratingDTO) {
        itemManagerService.rateSelectedItem(selectedItemId, ratingDTO);
        return ResponseEntity.ok("Selected Item rated successfully");
    }




}
