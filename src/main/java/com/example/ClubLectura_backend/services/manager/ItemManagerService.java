package com.example.ClubLectura_backend.services.manager;

import com.example.ClubLectura_backend.DTOs.CardItemDTO;
import com.example.ClubLectura_backend.DTOs.ItemDTO;
import com.example.ClubLectura_backend.DTOs.RatingDTO;
import com.example.ClubLectura_backend.DTOs.SelectedItemNewDTO;
import com.example.ClubLectura_backend.entities.*;
import com.example.ClubLectura_backend.repositories.ClubRepository;
import com.example.ClubLectura_backend.services.*;
import com.example.ClubLectura_backend.services.impl.ClubServiceImpl;
import com.example.ClubLectura_backend.services.impl.RatingServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemManagerService {

    @Autowired
    ItemService itemService;
    @Autowired
    SelectedItemService selectedItemService;
    @Autowired
    RatingService ratingService;
    @Autowired
    ClubService clubService;
    @Autowired
    ClubMembershipService clubMembershipService;


    public CardItemDTO createCardItemDTO(long selectedItemId, long clubMemberId) {

        SelectedItem se = selectedItemService.findById(selectedItemId)
                .orElseThrow(() -> new EntityNotFoundException("Selected Item not found"));

        /*  Descomentar si da un LazyInitializationException
        Item item = itemService.findById(se.getItem().getId())
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));
         */

        Item item = se.getItem();
        Optional<Rating> ratingOpt = ratingService.findByClubMember_IdAndSelectedItem_Id(clubMemberId, selectedItemId);

        int score = -1;
        if(ratingOpt.isPresent()) {
            Rating rating = ratingOpt.get();
            score = rating.getScore();
        }

        return new CardItemDTO(
                item.getName(),
                item.getLocation(),
                item.getAuthor(),
                item.getReleaseDate(),
                se.getStartDate(),
                se.getEndDate(),
                score,
                se.getId()
        );
    }

    public CardItemDTO createSelectedItem(SelectedItemNewDTO newSelectedItem) {

        Item item = itemService.findById(newSelectedItem.getItemId())
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));
        Club club = clubService.getReferenceById(newSelectedItem.getClubId());

        SelectedItem seSaved = selectedItemService.save(new SelectedItem(
                item,
                club,
                newSelectedItem.getStartDate(),
                newSelectedItem.getEndDate(),
                true
        ));

        return new CardItemDTO(
                item.getName(),
                item.getLocation(),
                item.getAuthor(),
                item.getReleaseDate(),
                newSelectedItem.getStartDate(),
                newSelectedItem.getEndDate(),
                -1,
                seSaved.getId()

        );
    }

    public void rateSelectedItem(RatingDTO ratingDTO) {
        Optional<Rating> existing = ratingService.findByClubMember_IdAndSelectedItem_Id(
                ratingDTO.getClubMemberId(), ratingDTO.getSelectedItemId());

        SelectedItem se = selectedItemService.getReferenceById(ratingDTO.getSelectedItemId());
        ClubMembership cm = clubMembershipService.getReferenceById(ratingDTO.getClubMemberId());

        if(existing.isPresent()) {
            Rating rating = existing.get();
            rating.setScore(ratingDTO.getScore());
            ratingService.save(rating);
        } else {
            ratingService.save(new Rating(
                    se,
                    cm,
                    ratingDTO.getScore(),
                    ratingDTO.getDate()
            ));
        }






    }


}
