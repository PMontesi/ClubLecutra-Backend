package com.example.ClubLectura_backend.services.manager;

import com.example.ClubLectura_backend.DTOs.ClubDTO;
import com.example.ClubLectura_backend.DTOs.ClubDTOResponse;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public ClubDTOResponse createClub(ClubDTO dto) {
        Club newClub = new Club();
        AppUser creator = appUserService.findById(dto.getCreatorId())
                .orElseThrow(() -> new EntityNotFoundException("User with " + dto.getCreatorId() + " id doesn't exist"));

        newClub.setCreator(creator);
        newClub.setName(dto.getName());
        newClub.setType(dto.getType());
        newClub.setCreationDate(LocalDate.now());
        newClub = clubService.save(newClub);

        ClubMembership creatorMemebership = clubMembershipService.createMember(creator, newClub, true);

        if (dto.getUsersInvited() != null && !dto.getUsersInvited().isEmpty()) {
            Set<String> invitedUsers = new HashSet<>(dto.getUsersInvited());
            invitedUsers.remove(creator.getEmail());
            this.inviteAppUsersToClub(invitedUsers, newClub);
        }

        return new ClubDTOResponse(
                newClub.getId(),
                creatorMemebership.getId(),
                newClub.getName()
        );
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

    public ClubMembership addMemberByUserId(long idUser, long idClub) {
        AppUser user = appUserService.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User with " + idUser + " id doesn't exist"));
        Club club = clubService.findById(idClub)
                .orElseThrow(() -> new EntityNotFoundException("Club with " + idClub + " id doesn't exist"));

        return clubMembershipService.createMember(user, club, false);
    }

    // Todo cambiar por el tema invitaciones
    public void inviteAppUsersToClub(Set<String> emailList, Club club) {
        for (String email : emailList) {
            Optional<AppUser> maybeAppUser = appUserService.findByEmail(email);

            if (maybeAppUser.isPresent()) {
                try {
                    AppUser foundAppUser = maybeAppUser.get();
                    ClubMembership newMember = clubMembershipService.createMember(foundAppUser, club, false);
                    club.addClubMembership(newMember);
                } catch (Exception e) {
                    System.out.println("Error invitando a " + email + " " + e);
                }
            } else {
                System.out.println("El siguiente email no está en registrado :" + email);
            }
        }
    }

}

