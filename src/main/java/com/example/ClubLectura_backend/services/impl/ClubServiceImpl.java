package com.example.ClubLectura_backend.services.impl;

import com.example.ClubLectura_backend.entities.Club;
import com.example.ClubLectura_backend.repositories.ClubRepository;
import com.example.ClubLectura_backend.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubServiceImpl implements ClubService {

    //Attributes
    @Autowired
    ClubRepository clubRepository;

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



    /*
    todo Lista de métodos de Club a añadir

    Hay que dividir los métodos en dos grupos: los del administrador y los normales
    100% que hay que autowirear el repo ClubMembership

    Seguramente haya que ampliar el modelo del club y/o el del usuario
    para meter las estadísticas (tipo genéro mas leido, cantidad de páginas, etc.).
    La opción B es usar la tabla de membership para almacenar las estadísticas de cada meimbro y
    después hacer las estadísticas del grupo


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
