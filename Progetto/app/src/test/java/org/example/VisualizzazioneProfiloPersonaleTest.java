package org.example;

import org.example.Api.Controllers.UserController;
import org.example.Api.Models.Response.UserResponse;
import org.example.Application.Service.UserService;
import org.example.Application.Validator.UserValidator;
import org.example.Core.enums.RuoloUser;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Team;
import org.example.Core.models.User;
import org.example.Infrastructure.Repository.*;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VisualizzazioneProfiloPersonaleTest {

  /*  private UserController controller;
    private UserService service;
    private UnitOfWork unitOfWork;
    private Long idUser;

    @Before
    public void setUp() {
        unitOfWork = new UnitOfWork(
                new HackathonRepository(),
                new TeamRepository(),
                new InvitoRepository(),
                new SegnalazioneRepository(),
                new UserRepository()
        );

        service = new UserService(unitOfWork, new UserValidator(unitOfWork));
        controller = new UserController(service);

        User user = createTestUserCompleto();
        unitOfWork.userRepository().create(user);
        unitOfWork.saveChanges();
        idUser = user.getId();
    }

    @Test
    public void testVisualizzazioneProfiloCompleto() {
        UserResponse dto = controller.visualizzaProfilo(idUser);

        Assert.assertNotNull(dto);
        Assert.assertEquals("Mario", dto.getNome());
        Assert.assertEquals("Rossi", dto.getCognome());
        Assert.assertEquals("mario@esempio.it", dto.getEmail());
        Assert.assertEquals(LocalDate.of(2000, 1, 1), dto.getDataNascita());
        Assert.assertEquals(RuoloUser.UTENTE_NON_ISCRITTO, dto.getRuolo());
        Assert.assertEquals("Team Alpha", dto.getTeamNome());

    }

    @Test
    public void testVisualizzazioneProfiloSenzaTeam() {
        User user = createTestUserBase(2L);
        user.setTeam(null);

        unitOfWork.userRepository().create(user);
        unitOfWork.saveChanges();

        UserResponse dto = controller.visualizzaProfilo(2L);

        Assert.assertNotNull(dto);
        Assert.assertEquals("-", dto.getTeamNome());
    }

    @Test
    public void testVisualizzazioneProfiloUserInesistente() {
        try {
            controller.visualizzaProfilo(999L);
            Assert.fail("Doveva lanciare un'eccezione per utente inesistente");
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().toLowerCase().contains("utente"));
        }
    }

    @Test
    public void testVisualizzazioneProfiloTeamPresenteHackathonAssente() {
        User user = createTestUserBase(3L);

        Team team = createTestTeam();
        user.setTeam(team);

        user.setHackathons(new ArrayList<>());

        unitOfWork.userRepository().create(user);
        unitOfWork.saveChanges();

        UserResponse dto = controller.visualizzaProfilo(3L);

        Assert.assertNotNull(dto);
        Assert.assertEquals("Team Alpha", dto.getTeamNome());
    }


    private User createTestUserCompleto() {
        User user = createTestUserBase(1L);

        Team team = createTestTeam();
        user.setTeam(team);

        Hackathon hackathon = createTestHackathon();
        List<Hackathon> hackathons = new ArrayList<>();
        hackathons.add(hackathon);
        user.setHackathons(hackathons);

        return user;
    }

    private User createTestUserBase(Long id) {
        User user = new User(id, RuoloUser.UTENTE_NON_ISCRITTO);
        user.setNome("Mario");
        user.setCognome("Rossi");
        user.setEmail("mario@esempio.it");
        user.setPassword("password1"); // serve al validator in altri contesti
        user.setCellulare("3333333333");
        user.setDataNascita(LocalDate.of(2000, 1, 1));
        return user;
    }

    private Team createTestTeam() {
        Team team = new Team();
        team.setId(10L);
        team.setNome("Team Alpha");
        team.setDataCreazione(LocalDate.now());
        return team;
    }

    private Hackathon createTestHackathon() {
        Hackathon hackathon = new Hackathon();
        hackathon.setId(7L);
        hackathon.setNome("HackHub Test");
        hackathon.setArgomento("AI");
        hackathon.setDataInizio(LocalDate.now().plusDays(10));
        hackathon.setDataFine(LocalDate.now().plusDays(12));
        hackathon.setLuogo("Milano");
        hackathon.setPremio(5000.0);
        return hackathon;
    }*/
}
