package com.example.ClubLectura_backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class ClubMenuDTO {
//Todo cambiar los métodos para incluir la id del usuario miembro del club
    private Long clubId;
    private String clubName;
    private Long selectedItemId;
    private String itemName;
    private Long userClubMemberId;
    private Boolean isAdmin;
    //private String itemImage;


    public ClubMenuDTO(Long clubId, String clubName, Long selectedItemId, String itemName, Long userClubMemberId, Boolean isAdmin) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.selectedItemId = selectedItemId;
        this.itemName = itemName;
        this.userClubMemberId = userClubMemberId;
        this.isAdmin = isAdmin;
    }
}
