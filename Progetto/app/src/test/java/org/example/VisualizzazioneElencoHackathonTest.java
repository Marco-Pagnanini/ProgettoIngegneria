package org.example;

import org.example.Api.Controllers.HackathonController;
import org.example.Application.Service.HackathonService;
import org.example.Application.Validator.HackathonValidator;
import org.example.Core.models.Hackathon;
import org.example.Infrastructure.Repository.*;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class VisualizzazioneElencoHackathonTest {

    private HackathonController controller;
    private HackathonService service;
    private UnitOfWork unitOfWork;

    @Before
    public void setUp() {
        unitOfWork = new UnitOfWork(
                new HackathonRepository(),
                new TeamRepository(),
                new InvitoRepository(),
                new SegnalazioneRepository(),
                new UserRepository()
        );

        // Se non hai validator per Hackathon, puoi passare null o rimuoverlo dal service
        service = new HackathonService(unitOfWork, new HackathonValidator());
        controller = new HackathonController(service);
    }

    @Test
    public void testVisualizzazioneElencoHackathonVuoto() {
        List<Hackathon> hackathons = controller.visualizzaHackathon();

        Assert.assertNotNull(hackathons);
        Assert.assertTrue(hackathons.isEmpty());
    }

    @Test
    public void testVisualizzazioneElencoHackathonConUno() {
        Hackathon h1 = createTestHackathon(1L, "Hackathon 1");
        unitOfWork.hackathonRepository().create(h1);
        unitOfWork.saveChanges();

        List<Hackathon> hackathons = controller.visualizzaHackathon();

        Assert.assertNotNull(hackathons);
        Assert.assertEquals(1, hackathons.size());
        Assert.assertEquals("Hackathon 1", hackathons.get(0).getNome());
    }

    @Test
    public void testVisualizzazioneElencoHackathonConMultipli() {
        for (int i = 1; i <= 5; i++) {
            Hackathon h = createTestHackathon((long) i, "Hackathon " + i);
            unitOfWork.hackathonRepository().create(h);
        }
        unitOfWork.saveChanges();

        List<Hackathon> hackathons = controller.visualizzaHackathon();

        Assert.assertNotNull(hackathons);
        Assert.assertEquals(5, hackathons.size());
    }

    @Test
    public void testVisualizzazioneElencoHackathonIndipendenteDaAltriDati() {
        // Creo hackathon
        Hackathon h1 = createTestHackathon(10L, "Hack indipendente");
        unitOfWork.hackathonRepository().create(h1);

        // Creo anche un team e un user, per verificare che non “rompa” la visualizzazione elenco
        // (non serve che siano collegati)
        unitOfWork.teamRepository().create(createTestTeam(99L, "TeamX"));
        unitOfWork.userRepository().create(createTestUser(77L, "luigi@esempio.it"));

        unitOfWork.saveChanges();

        List<Hackathon> hackathons = controller.visualizzaHackathon();

        Assert.assertNotNull(hackathons);
        Assert.assertEquals(1, hackathons.size());
        Assert.assertEquals("Hack indipendente", hackathons.get(0).getNome());
    }



    private Hackathon createTestHackathon(Long id, String nome) {
        Hackathon hackathon = new Hackathon();
        //hackathon.setId(id);
        hackathon.setNome(nome);
        hackathon.setArgomento("AI");
        hackathon.setDataInizio(LocalDate.now().plusDays(10));
        hackathon.setDataFine(LocalDate.now().plusDays(12));
        hackathon.setLuogo("Milano");
        hackathon.setPremio(5000.0);
        return hackathon;
    }

    private org.example.Core.models.Team createTestTeam(Long id, String nome) {
        org.example.Core.models.Team team = new org.example.Core.models.Team();
        team.setId(id);
        team.setNome(nome);
        team.setDataCreazione(LocalDate.now());
        return team;
    }

    private org.example.Core.models.User createTestUser(Long id, String email) {
        org.example.Core.models.User user =
                new org.example.Core.models.User(id, org.example.Core.enums.RuoloUser.UTENTE_NON_ISCRITTO);
        user.setEmail(email);
        user.setNome("Luigi");
        user.setCognome("Bianchi");
        user.setPassword("password1");
        user.setCellulare("3333333333");
        user.setDataNascita(LocalDate.of(2000, 1, 1));
        return user;
    }
}
