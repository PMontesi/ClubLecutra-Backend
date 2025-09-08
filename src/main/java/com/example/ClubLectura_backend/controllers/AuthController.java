package com.example.ClubLectura_backend.controllers;

import com.example.ClubLectura_backend.DTOs.AuthResponse;
import com.example.ClubLectura_backend.DTOs.LoginRequest;
import com.example.ClubLectura_backend.repositories.AppUserRepository;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ClubLectura_backend.security.jwt.JwtService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final AppUserRepository appUserRepository;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, AppUserRepository appUserRepository) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        UserDetails principal = (UserDetails) auth.getPrincipal();
        //Set<String> roles = appUserRepository.findByEmail(principal.getUsername()).get().getRoles(); //Para el futuro
        String token = jwtService.generateToken(principal.getUsername(), Map.of()); //Añadir roles en el futuro
        return new AuthResponse(token, principal.getUsername(), null);
    }
}
