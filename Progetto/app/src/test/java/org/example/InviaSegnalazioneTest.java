package org.example;

import org.example.Api.Controllers.SegnalazioneController;
import org.example.Api.Models.Request.SegnalazioneRequest;
import org.example.Application.Service.SegnalazioneService;
import org.example.Application.Validator.SegnalazioneValidator;
import org.example.Core.enums.StatoSegnalazione;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Segnalazione;
import org.example.Core.models.Team;
import org.example.Infrastructure.Repository.*;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class InviaSegnalazioneTest {
    private SegnalazioneController segnalazioneController;
    private SegnalazioneService segnalazioneService;
    private UnitOfWork unitOfWork;

    private Long idHackathon;
    private Long idTeam;

    @Before
    public void setUp() {
        unitOfWork = new UnitOfWork(
                new HackathonRepository(),
                new TeamRepository(),
                new InvitoRepository(),
                new SegnalazioneRepository(),
                new UserRepository(),
                new SottoMissioniRepository(),
                new RispostaRepository(),
                new ValutazioneRepository()
        );

        segnalazioneService = new SegnalazioneService(unitOfWork,  new SegnalazioneValidator());
        segnalazioneController =  new SegnalazioneController(segnalazioneService);

        Hackathon hackathon = createTestHackathon();
        hackathon.setSegnalazioni(new ArrayList<>());
        hackathon.setTeams(new ArrayList<>());

        unitOfWork.hackathonRepository().create(hackathon);
        unitOfWork.saveChanges();
        idHackathon = hackathon.getId();

        Team team = createTestTeam("Team valido");
        unitOfWork.teamRepository().create(team);
        unitOfWork.saveChanges();
        idTeam = team.getId();

        Hackathon persisted = unitOfWork.hackathonRepository().getById(idHackathon);
        persisted.getTeams().add(team);
        unitOfWork.hackathonRepository().update(persisted);
        unitOfWork.saveChanges();
    }

    @Test
    public void testInvioSegnalazioneSuccesso(){
        SegnalazioneRequest request = createValidRequest("Titolo" , "Descrizione", idTeam, 1L);

        Segnalazione segnalazione = segnalazioneController.addSegnalazione(idHackathon, request);

        Assert.assertNotNull(segnalazione);
        Assert.assertEquals("Titolo", segnalazione.getNome());
        Assert.assertEquals("Descrizione", segnalazione.getDescrizione());
        Assert.assertEquals(StatoSegnalazione.APERTA, segnalazione.getStatoSegnalazione());

        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        Assert.assertNotNull(hackathon.getSegnalazioni());
        Assert.assertEquals(1, hackathon.getSegnalazioni().size());
    }

    @Test
    public void testInvioSegnalazioneDatiNonValidi() {

        SegnalazioneRequest request = createValidRequest("Titolo", "   ", idTeam, 1L);

        Segnalazione segnalazione = segnalazioneController.addSegnalazione(idHackathon, request);

        Assert.assertNull(segnalazione);

        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        Assert.assertTrue(hackathon.getSegnalazioni().isEmpty());
    }

    @Test
    public void testInvioSegnalazioneTeamNonAppartieneHackathon() {

        Team teamFuori = createTestTeam("Team Fuori");
        unitOfWork.teamRepository().create(teamFuori);
        unitOfWork.saveChanges();
        Assert.assertNotEquals(idTeam, teamFuori.getId());

        SegnalazioneRequest request = createValidRequest("Titolo", "Descrizione", teamFuori.getId(), 1L);

        Segnalazione segnalazione = segnalazioneController.addSegnalazione(idHackathon, request);

        Assert.assertNull(segnalazione);

        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        Assert.assertTrue(hackathon.getSegnalazioni().isEmpty());
    }

    @Test
    public void testInvioSegnalazioneHackathonInesistente() {
        SegnalazioneRequest request = createValidRequest("Titolo", "Descrizione", idTeam, 1L);

        Segnalazione segnalazione = segnalazioneController.addSegnalazione(999L, request);

        Assert.assertNull(segnalazione);
    }

    @Test
    public void testInvioSegnalazioneTeamInesistente() {
        SegnalazioneRequest request = createValidRequest("Titolo", "Descrizione", 9999L, 1L);

        Segnalazione segnalazione = segnalazioneController.addSegnalazione(idHackathon, request);

        Assert.assertNull(segnalazione);

        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        Assert.assertTrue(hackathon.getSegnalazioni().isEmpty());
    }




    private SegnalazioneRequest createValidRequest(String nome, String descrizione, Long idTeam, Long idMentore) {
        SegnalazioneRequest request = new SegnalazioneRequest();
        request.setNome(nome);
        request.setDescrizione(descrizione);
        request.setIdTeamSegnalazione(idTeam);
        request.setIdMentore(idMentore);
        return request;
    }

    private Team createTestTeam(String nome) {
        Team team = new Team();
        team.setNome(nome);
        return team;
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
