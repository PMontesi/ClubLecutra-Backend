package com.example.ClubLectura_backend.controllers;

import com.example.ClubLectura_backend.entities.ClubMembership;
import com.example.ClubLectura_backend.services.impl.ClubMembershipServiceImpl;
import com.example.ClubLectura_backend.services.manager.ClubManagerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/clubmemberships")
public class ClubMembershipController {

    //Attributes
    @Autowired
    ClubManagerService clubManagerService;
    @Autowired
    ClubMembershipServiceImpl clubMembershipService;

    @PostMapping
    public ResponseEntity<?> addMember(@RequestParam long userId, @RequestParam long clubId) {
        try {
            ClubMembership newMember = clubManagerService.addMemberByUserId(userId, clubId);
            return ResponseEntity.ok(newMember);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error" + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> leaveMemeber(@RequestParam long userId, @RequestParam long clubId) {
        try {
            boolean result = clubMembershipService.leaveClub(userId, clubId);

            if (result) {
                return ResponseEntity.ok("You left the club");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("You are the admin. Please assign a new admin before leaving");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membership not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occured");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubMembership> getById(@PathVariable long clubMemeberId) {
        return clubMembershipService.findById(clubMemeberId)
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    /* REVISAR
    @GetMapping("/{appUserId}")
    public ResponseEntity<ClubMembership> getByAppUserId(@PathVariable long appUserId) {
        return clubMembershipService.findByAppUser_Id(appUserId)
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

     */

    @PutMapping("/admin")
    public ResponseEntity<?> changeAdmin(@RequestParam long adminMemberId, @RequestParam long newAdminMemberId) {
        boolean result = clubMembershipService.changeAdmin(adminMemberId, newAdminMemberId);

        if (result) {
            return ResponseEntity.ok("Admin changed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admin change failed: one or both members not found");
        }
    }
}
