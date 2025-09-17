package com.example.ClubLectura_backend.controllers;

import com.example.ClubLectura_backend.DTOs.AppUserDTO;
import com.example.ClubLectura_backend.DTOs.AuthResponse;
import com.example.ClubLectura_backend.DTOs.LoginRequest;
import com.example.ClubLectura_backend.repositories.AppUserRepository;
import com.example.ClubLectura_backend.services.impl.AppUserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final AppUserServiceImpl appUserRepository;
    public AuthController(AuthenticationManager authManager, JwtService jwtService, AppUserServiceImpl appUserRepository) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req){
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
            );
            UserDetails principal = (UserDetails) auth.getPrincipal();
            AppUserDTO user = appUserRepository.createDTO(appUserRepository.findByEmail(principal.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado")));
            //Set<String> roles = appUserRepository.findByEmail(principal.getUsername()).get().getRoles(); //Para el futuro
            String token = jwtService.generateToken(principal.getUsername(), Map.of()); //Añadir roles en el futuro

            return ResponseEntity.ok(new AuthResponse(
                    token,
                    principal.getUsername(), //Este es el email
                    null,
                    user.getId(),
                    user.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }



    }
}
