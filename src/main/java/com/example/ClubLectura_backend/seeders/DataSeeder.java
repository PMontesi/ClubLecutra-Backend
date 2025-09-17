package com.example.ClubLectura_backend.seeders;

import com.example.ClubLectura_backend.entities.*;
import com.example.ClubLectura_backend.repositories.*;
import com.example.ClubLectura_backend.services.impl.AppUserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final AppUserServiceImpl appUserService;
    private final ClubRepository clubRepository;
    private final ClubMembershipRepository clubMembershipRepository;
    private final ItemRepository itemRepository;
    private final SelectedItemRepository selectedItemRepository;
    private final RatingRepository ratingRepository;

    public DataSeeder(
            AppUserRepository appUserRepository,
            AppUserServiceImpl appUserService,
            ClubRepository clubRepository,
            ClubMembershipRepository clubMembershipRepository,
            ItemRepository itemRepository,
            SelectedItemRepository selectedItemRepository,
            RatingRepository ratingRepository
    ) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
        this.clubRepository = clubRepository;
        this.clubMembershipRepository = clubMembershipRepository;
        this.itemRepository = itemRepository;
        this.selectedItemRepository = selectedItemRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (appUserRepository.count() == 0) {

            AppUser pablo = new AppUser("Pablo", "pablo@pablo.com", "admin1234");
            AppUser juan = new AppUser("Juan", "juan@juan.com", "juan1234");
            AppUser teresa = new AppUser("Teresa", "teresa@teresa.com", "teresa1234");
            AppUser prueba = new AppUser("Prueba", "prueba@prueba.com", "prueba1234");

            appUserService.saveAppUser(pablo);
            appUserService.saveAppUser(juan);
            appUserService.saveAppUser(teresa);
            appUserService.saveAppUser(prueba);

            System.out.println("✅ 4 usuarios de prueba insertados en la base de datos.");


            if (clubRepository.count() == 0) {

                Club cine = new Club(pablo, "Club de cine", "film");
                Club lectura = new Club(prueba, "Club de lectura", "book");

                clubRepository.save(cine);
                clubRepository.save(lectura);

                System.out.println("✅ 2 clubs de prueba insertados en la base de datos.");

                if (clubMembershipRepository.count() == 0) {
                    ClubMembership pabloCine = new ClubMembership(pablo, cine, true);
                    ClubMembership juanCine = new ClubMembership(juan, cine, false);
                    ClubMembership teresaCine = new ClubMembership(teresa, cine, false);
                    ClubMembership pruebaCine = new ClubMembership(prueba, cine, false);

                    ClubMembership pabloLectura = new ClubMembership(pablo, lectura, true);
                    ClubMembership pruebaLectura = new ClubMembership(prueba, lectura, false);

                    clubMembershipRepository.save(pabloCine);
                    clubMembershipRepository.save(juanCine);
                    clubMembershipRepository.save(teresaCine);
                    clubMembershipRepository.save(pruebaCine);
                    clubMembershipRepository.save(pabloLectura);
                    clubMembershipRepository.save(pruebaLectura);

                    System.out.println("✅ 6 miembros de prueba insertados en la base de datos.");

                }

                if (selectedItemRepository.count() == 0) {

                    Item mission = new Item("Misión Imposible 1", "Película", "USA", "Brian De Palma", LocalDate.of(1996, 5, 22));
                    Item aliG = new Item("Ali G", "Película", "UK", "Mark Mylod", LocalDate.of(2002, 7, 26));

                    itemRepository.save(mission);
                    itemRepository.save(aliG);
                    itemRepository.save(new Item("Inception", "Película", "USA", "Christopher Nolan", LocalDate.of(2010, 7, 16)));
                    itemRepository.save(new Item("The Matrix", "Película", "USA", "Lana Wachowski, Lilly Wachowski", LocalDate.of(1999, 3, 31)));
                    itemRepository.save(new Item("The Godfather", "Película", "USA", "Francis Ford Coppola", LocalDate.of(1972, 3, 24)));
                    itemRepository.save(new Item("Pulp Fiction", "Película", "USA", "Quentin Tarantino", LocalDate.of(1994, 10, 14)));
                    itemRepository.save(new Item("The Dark Knight", "Película", "USA", "Christopher Nolan", LocalDate.of(2008, 7, 18)));
                    itemRepository.save(new Item("Forrest Gump", "Película", "USA", "Robert Zemeckis", LocalDate.of(1994, 7, 6)));
                    itemRepository.save(new Item("Fight Club", "Película", "USA", "David Fincher", LocalDate.of(1999, 10, 15)));
                    itemRepository.save(new Item("Interstellar", "Película", "USA", "Christopher Nolan", LocalDate.of(2014, 11, 7)));

                    System.out.println("✅ 10 películas de prueba insertadas en la base de datos.");

                    selectedItemRepository.save(new SelectedItem(mission, cine, true));
                    selectedItemRepository.save(new SelectedItem(aliG, cine, false));

                    System.out.println("✅ 2 item de club de prueba insertados en la base de datos.");


                }

            }

        }


    }
}
