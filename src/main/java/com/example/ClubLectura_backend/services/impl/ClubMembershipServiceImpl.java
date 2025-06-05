package com.example.ClubLectura_backend.services.impl;

import com.example.ClubLectura_backend.entities.AppUser;
import com.example.ClubLectura_backend.entities.Club;
import com.example.ClubLectura_backend.entities.ClubMembership;
import com.example.ClubLectura_backend.repositories.ClubMembershipRepository;
import com.example.ClubLectura_backend.services.ClubMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClubMembershipServiceImpl implements ClubMembershipService {

    //Attributes
    @Autowired
    ClubMembershipRepository clubMembershipRepository;

    //CRUD Methods
    @Override
    public Optional<ClubMembership> findById(long id) {
        return clubMembershipRepository.findById(id);
    }

    @Override
    public List<ClubMembership> findAll() {
        return clubMembershipRepository.findAll();
    }

    @Override
    public void save(ClubMembership clubMembership) {
        clubMembershipRepository.save(clubMembership);
    }

    @Override
    public void delete(long id) {
        clubMembershipRepository.deleteById(id);
    }

    @Override
    public void delete(ClubMembership clubMembership) {
        clubMembershipRepository.delete(clubMembership);
    }

    //Logic Methods

    public ClubMembership createMembership(AppUser user, Club club, boolean isOwner) {
        ClubMembership membership = new ClubMembership();

        membership.setAppUser(user);
        membership.setClub(club);
        membership.setOwner(isOwner);
        membership.setUnionDate(LocalDate.now());

        this.save(membership);

        return membership;
    }


    /*
    todo lista de métodos de ClubMemership a añadir

    Quizá sea buena idea almacenar las estadísticas de cada usuario en esta tabla
    y después sumarlas y hacer medias para obtener las estadísticas del grupo.

    -Añadir usuarios (admin)
        -Es posible que haya que comprobar si el usuario fue miembro alguna vez.

    -Salirse del grupo (general)
        -No es un método para echar usuarios.
        -Si es el admin el que se va, habrá que elegir otro.
            -Por lo tanto, hay que crear un método que seleccione automáticamente el siguiente admin.
            Método que seguramente sea del servicio del ClubMembership
        -Seguramente no haya que borrar al usuario, sino guardarlo como inactivo.

    -Cambiar de admin (admin)
        -El usuario deja de ser el admin.
        -Se tiene que elegir qué otro usuario será el admin.

    -Estadísticas del miembro
        -Consultar el archivo ClubService para más información.
     */
}
