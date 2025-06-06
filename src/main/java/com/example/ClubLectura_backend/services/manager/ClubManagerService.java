package com.example.ClubLectura_backend.services.manager;

import com.example.ClubLectura_backend.DTOs.ClubDTO;
import com.example.ClubLectura_backend.entities.AppUser;
import com.example.ClubLectura_backend.entities.Club;
import com.example.ClubLectura_backend.entities.ClubMembership;
import com.example.ClubLectura_backend.services.AppUserService;
import com.example.ClubLectura_backend.services.ClubMembershipService;
import com.example.ClubLectura_backend.services.ClubService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClubManagerService {

    private final ClubService clubService;
    private final ClubMembershipService clubMembershipService;
    private final AppUserService appUserService;

    @Autowired
    public ClubManagerService(
            ClubService clubService,
            ClubMembershipService clubMembershipService,
            AppUserService appUserService
    ) {
        this.clubService = clubService;
        this.clubMembershipService = clubMembershipService;
        this.appUserService = appUserService;
    }

    public Club createClub(ClubDTO dto) {
        Club newClub = new Club();
        AppUser creator = appUserService.findById(dto.getCreatorId())
                .orElseThrow(() -> new EntityNotFoundException("User with " + dto.getCreatorId() + " id doesn't exist"));

        newClub.setCreator(creator);
        newClub.setName(dto.getName());
        newClub.setType(newClub.getType()); //TODO no se pasa el tipo
        newClub.setCreationDate(LocalDate.now());
        newClub = clubService.save(newClub);

        clubMembershipService.createMember(creator, newClub, true);

        return newClub;
    }

    public boolean deleteClub(long clubId) {
        long totalMembers = clubMembershipService.countByClub_Id(clubId);

        if (totalMembers <= 1) {
            clubService.delete(clubId);
            return true;
        }
        else {
            return false;
        }
    }

    public ClubMembership addMember(long idUser, long idClub) {
        AppUser user = appUserService.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User with " + idUser + " id doesn't exist"));
        Club club = clubService.findById(idClub)
                .orElseThrow(() -> new EntityNotFoundException("Club with " + idClub + " id doesn't exist"));

        return clubMembershipService.createMember(user, club, false);
    }

}

