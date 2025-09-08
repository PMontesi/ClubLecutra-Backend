package com.example.ClubLectura_backend.services.userDetails;

import com.example.ClubLectura_backend.entities.AppUser;
import com.example.ClubLectura_backend.repositories.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetails implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public AppUserDetails(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser u = appUserRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));

        return User.withUsername(u.getUsername())
                .password(u.getPassword())
                .build();
    }
}
