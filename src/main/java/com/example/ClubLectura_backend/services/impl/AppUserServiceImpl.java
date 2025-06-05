package com.example.ClubLectura_backend.services.impl;

import com.example.ClubLectura_backend.entities.AppUser;
import com.example.ClubLectura_backend.repositories.AppUserRepository;
import com.example.ClubLectura_backend.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    //Attributes
    @Autowired
    AppUserRepository appUserRepository;

    //CRUD Methods
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

    //Logic Methods

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
