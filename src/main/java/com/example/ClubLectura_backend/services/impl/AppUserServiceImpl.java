package com.example.ClubLectura_backend.services.impl;

import com.example.ClubLectura_backend.DTOs.AppUserDTO;
import com.example.ClubLectura_backend.entities.AppUser;
import com.example.ClubLectura_backend.repositories.AppUserRepository;
import com.example.ClubLectura_backend.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    //Attributes
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    //CRUD basic Methods
    @Override
    public Optional<AppUser> findById(long id) {
        return appUserRepository.findById(id);
    }

    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public void save(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public void delete(long id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public void delete(AppUser appUser) {
        appUserRepository.delete(appUser);
    }

    //Other CRUD Methods
    @Override
    public Optional<AppUser> findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    //Logic Methods

    /*
        Actualizaciones que hacere al método register:
            -Que guarde más información del usuario
     */
    public AppUserDTO saveAppUser(AppUser newUser) {
        System.out.println(newUser.toString());
        String password = newUser.getPassword();
        newUser.setPassword(passwordEncoder.encode(password));
        this.save(newUser);

        return this.createDTO(newUser);
    }

    public AppUserDTO createDTO(AppUser user) {
        AppUserDTO dto = new AppUserDTO();

        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setClubsCreated(user.getClubsCreated());
        dto.setMemberships(user.getMemberships());


        return dto;
    }

        /*
        todo Lista de métodos de AppUser a añadir

        -Método para obtener todas las estadísticas relacionadas con el usuario
            -Requiere del diseño de la página personal del usuario.
            -Dejárlo casi de los últimos

        -Método para obtener la información básica del usuario

        -Método para cambiar la información básica del usuario
            -la contraseña quizá necesite un método aparte


     */


}
