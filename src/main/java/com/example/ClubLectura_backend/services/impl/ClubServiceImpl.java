package com.example.ClubLectura_backend.services.impl;

import com.example.ClubLectura_backend.DTOs.ClubDTO;
import com.example.ClubLectura_backend.entities.AppUser;
import com.example.ClubLectura_backend.entities.Club;
import com.example.ClubLectura_backend.repositories.ClubRepository;
import com.example.ClubLectura_backend.services.ClubService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClubServiceImpl implements ClubService {

    //Attributes
    @Autowired
    ClubRepository clubRepository;
    @Autowired
    ClubMembershipServiceImpl clubMembershipService;
    @Autowired
    AppUserServiceImpl appUserService;

    //CRUD Methods
    @Override
    public Optional<Club> findById(long id) {
        return clubRepository.findById(id);
    }

    @Override
    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    @Override
    public Club save(Club club) {
        return clubRepository.save(club);
    }

    @Override
    public void delete(long id) {
        clubRepository.deleteById(id);
    }

    @Override
    public void delete(Club club) {
        clubRepository.delete(club);
    }

    //Logic Methods

    public Club createClub(ClubDTO dto) {
        Club newClub = new Club();
        AppUser creator = appUserService.findById(dto.getCreatorId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        newClub.setCreator(creator);
        newClub.setName(dto.getName());
        newClub.setType(newClub.getType());
        newClub.setCreationDate(LocalDate.now());
        newClub = this.save(newClub);

        clubMembershipService.createMembership(creator, newClub,true);

        return newClub;
    }

    /*
    todo Lista de métodos de Club a añadir

    Hay que dividir los métodos en dos grupos: los del administrador y los normales
    100% que hay que autowirear el repo ClubMembership

    Seguramente haya que ampliar el modelo del club y/o el del usuario
    para meter las estadísticas (tipo genéro mas leido, cantidad de páginas, etc.).
    La opción B es usar la tabla de membership para almacenar las estadísticas de cada meimbro y
    después hacer las estadísticas del grupo

    -Crear un grupo
        -Requiere la id del usuario para crearse y guardarla como administrador.
            -Habrá que usar el servicio del ClubMembership
        -Requiere elegir el tipo de items a los que se tendrá acceso (por ahora películas)

    -Borrar el club (admin method)
        -Requiere que sea el único miembro del club

    -Consultar las estadísticas del club (general)
        -Por ahora solo:
            -listado de items y cantidad
            -item mejor y peor valorado
            -Qué item propuso qué usuario
            -Usuarios cuyos items tienen la mejor/peor valoración
        -Ampliable según se amplien las estadísticas
     */
}
