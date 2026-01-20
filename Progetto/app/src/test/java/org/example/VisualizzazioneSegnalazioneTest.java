package org.example;

import org.example.Api.Controllers.SegnalazioneController;
import org.example.Application.Service.SegnalazioneService;
import org.example.Application.Validator.SegnalazioneValidator;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Segnalazione;
import org.example.Infrastructure.Repository.HackathonRepository;
import org.example.Infrastructure.Repository.InvitoRepository;
import org.example.Infrastructure.Repository.TeamRepository;
import org.example.Infrastructure.Repository.UserRepository;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VisualizzazioneSegnalazioneTest {
    private SegnalazioneController controller;
    private SegnalazioneService service;
    private UnitOfWork unitOfWork;
    private Long idHackathon;

    @Before
    public void setUp() {
        unitOfWork = new UnitOfWork(
                new HackathonRepository(),
                new TeamRepository(),
                new InvitoRepository(),
                new UserRepository()
        );
        service = new SegnalazioneService(unitOfWork, new SegnalazioneValidator());
        controller = new SegnalazioneController(service);

        Hackathon hackathon = createTestHackathon();
        unitOfWork.hackathonRepository().create(hackathon);
        unitOfWork.saveChanges();
        idHackathon = hackathon.getId();
    }

    @Test
    public void testVisualizzazioneTutteSegnalazioni() {
        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        hackathon.setSegnalazioni(new ArrayList<>());

        Segnalazione seg1 = createTestSegnalazione("Segnalazione 1");
        Segnalazione seg2 = createTestSegnalazione("Segnalazione 2");

        hackathon.getSegnalazioni().add(seg1);
        hackathon.getSegnalazioni().add(seg2);

        unitOfWork.hackathonRepository().update(hackathon);
        unitOfWork.saveChanges();

        List<Segnalazione> segnalazioni = controller.getAllSegnalazioni(idHackathon);

        Assert.assertNotNull(segnalazioni);
        Assert.assertEquals(2, segnalazioni.size());
    }

    @Test
    public void testVisualizzazioneSegnalazioniHackathonVuoto() {
        List<Segnalazione> segnalazioni = controller.getAllSegnalazioni(idHackathon);

        Assert.assertNotNull(segnalazioni);
        Assert.assertTrue(segnalazioni.isEmpty());
    }

    @Test
    public void testVisualizzazioneSegnalazioniHackathonInesistente() {
        List<Segnalazione> segnalazioni = controller.getAllSegnalazioni(999L);

        Assert.assertNotNull(segnalazioni);
        Assert.assertTrue(segnalazioni.isEmpty());
    }

    @Test
    public void testVisualizzazioneMultipleSegnalazioni() {
        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        hackathon.setSegnalazioni(new ArrayList<>());

        for(int i = 0; i < 5; i++) {
            Segnalazione segnalazione = createTestSegnalazione("Segnalazione " + i);
            hackathon.getSegnalazioni().add(segnalazione);
        }

        unitOfWork.hackathonRepository().update(hackathon);
        unitOfWork.saveChanges();

        List<Segnalazione> segnalazioni = controller.getAllSegnalazioni(idHackathon);

        Assert.assertEquals(5, segnalazioni.size());
    }

    @Test
    public void testVisualizzazioneSegnalazioniDiversiHackathon() {
        Hackathon hackathon2 = createTestHackathon();
        hackathon2.setNome("Hackathon 2");
        unitOfWork.hackathonRepository().create(hackathon2);
        unitOfWork.saveChanges();
        Long idHackathon2 = hackathon2.getId();

        Hackathon hack1 = unitOfWork.hackathonRepository().getById(idHackathon);
        hack1.setSegnalazioni(new ArrayList<>());
        hack1.getSegnalazioni().add(createTestSegnalazione("Segnalazione Hack 1"));
        unitOfWork.hackathonRepository().update(hack1);
        unitOfWork.saveChanges();

        Hackathon hack2 = unitOfWork.hackathonRepository().getById(idHackathon2);
        hack2.setSegnalazioni(new ArrayList<>());
        hack2.getSegnalazioni().add(createTestSegnalazione("Segnalazione Hack 2"));
        unitOfWork.hackathonRepository().update(hack2);
        unitOfWork.saveChanges();

        List<Segnalazione> segnalazioniHack1 = controller.getAllSegnalazioni(idHackathon);
        List<Segnalazione> segnalazioniHack2 = controller.getAllSegnalazioni(idHackathon2);

        Assert.assertEquals(1, segnalazioniHack1.size());
        Assert.assertEquals(1, segnalazioniHack2.size());
    }

    private Segnalazione createTestSegnalazione(String nome) {
        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setNome(nome);
        segnalazione.setDescrizione("Descrizione di " + nome);
        return segnalazione;
    }

    private Hackathon createTestHackathon() {
        Hackathon hackathon = new Hackathon();
        hackathon.setNome("Test Hackathon");
        hackathon.setArgomento("AI");
        hackathon.setDataInizio(LocalDate.now().plusDays(10));
        hackathon.setDataFine(LocalDate.now().plusDays(12));
        hackathon.setLuogo("Milano");
        hackathon.setPremio(5000.0);
        return hackathon;
    }
}
