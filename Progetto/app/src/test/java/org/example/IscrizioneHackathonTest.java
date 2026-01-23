package org.example;

import org.example.Api.Controllers.HackathonController;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Application.Service.HackathonService;
import org.example.Core.enums.RuoloUser;
import org.example.Core.enums.State;
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

public class IscrizioneHackathonTest {

    private HackathonController controller;
    private HackathonService service;
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

        service = new HackathonService(unitOfWork, new AlwaysValidHackathonValidator());
        controller = new HackathonController(service);

        Hackathon hackathon = createHackathonIscrivibile();
        unitOfWork.hackathonRepository().create(hackathon);
        unitOfWork.saveChanges();
        idHackathon = hackathon.getId();

        Team team = createTeamConNMembri(2);
        unitOfWork.teamRepository().create(team);
        unitOfWork.saveChanges();
        idTeam = team.getId();
    }

    @Test
    public void testIscrizioneTeamSuccesso() {
        Hackathon updated = controller.iscrizioneTeam(idTeam, idHackathon);

        Assert.assertNotNull(updated);
        Assert.assertEquals(idHackathon, updated.getId());
        Assert.assertEquals(1, updated.getTeams().size());

        Team teamRepo = unitOfWork.teamRepository().getById(idTeam);
        Assert.assertTrue(updated.getTeams().contains(teamRepo));
    }

    @Test
    public void testIscrizioneHackathonInesistente() {
        Hackathon updated = controller.iscrizioneTeam(idTeam, 999L);
        Assert.assertNull(updated);
    }

    @Test
    public void testIscrizioneTeamInesistente() {
        Hackathon updated = controller.iscrizioneTeam(999L, idHackathon);
        Assert.assertNull(updated);
    }

    @Test
    public void testIscrizioneHackathonNonInIscrizione() {
        Hackathon h = unitOfWork.hackathonRepository().getById(idHackathon);
        h.setStato(State.IN_CORSO);
        unitOfWork.hackathonRepository().update(h);
        unitOfWork.saveChanges();

        Hackathon updated = controller.iscrizioneTeam(idTeam, idHackathon);
        Assert.assertNull(updated);
    }

    @Test
    public void testIscrizioneTeamGiaIscritto() {
        Hackathon h = unitOfWork.hackathonRepository().getById(idHackathon);
        Team teamRepo = unitOfWork.teamRepository().getById(idTeam);

        h.getTeams().add(teamRepo);

        unitOfWork.hackathonRepository().update(h);
        unitOfWork.saveChanges();

        Hackathon updated = controller.iscrizioneTeam(idTeam, idHackathon);
        Assert.assertNull(updated);
    }

    @Test
    public void testIscrizioneTeamSuperaDimensioneMassimaTeam() {
        Hackathon h = unitOfWork.hackathonRepository().getById(idHackathon);
        h.setDimensioneMassimaTeam(2); // team ha 3 persone -> fallisce
        unitOfWork.hackathonRepository().update(h);
        unitOfWork.saveChanges();

        Hackathon updated = controller.iscrizioneTeam(idTeam, idHackathon);
        Assert.assertNull(updated);
    }

    @Test
    public void testIscrizioneTeamSottoDimensioneMinimaTeam() {
        Team teamPiccolo = createTeamConNMembri(0);
        unitOfWork.teamRepository().create(teamPiccolo);
        unitOfWork.saveChanges();

        Hackathon h = unitOfWork.hackathonRepository().getById(idHackathon);
        h.setDimensioneMinimaTeam(2); // almeno 2 persone
        unitOfWork.hackathonRepository().update(h);
        unitOfWork.saveChanges();

        Hackathon updated = controller.iscrizioneTeam(teamPiccolo.getId(), idHackathon);
        Assert.assertNull(updated);
    }

    @Test
    public void testIscrizioneSuperaNumeroMassimoPersoneHackathon() {
        Hackathon h = unitOfWork.hackathonRepository().getById(idHackathon);
        h.setNumeroMassimoPersone(2); // team ha 3 persone -> fallisce
        unitOfWork.hackathonRepository().update(h);
        unitOfWork.saveChanges();

        Hackathon updated = controller.iscrizioneTeam(idTeam, idHackathon);
        Assert.assertNull(updated);
    }

    @Test
    public void testIscrizioneHackathonGiaPienoConAltriTeam() {
        Hackathon h = unitOfWork.hackathonRepository().getById(idHackathon);
        h.setNumeroMassimoPersone(5);

        // team già iscritto con 4 persone (3 membri + leader)
        Team teamGrande = createTeamConNMembri(3);
        unitOfWork.teamRepository().create(teamGrande);
        unitOfWork.saveChanges();

        Team teamGrandeRepo = unitOfWork.teamRepository().getById(teamGrande.getId());
        h.getTeams().add(teamGrandeRepo);

        unitOfWork.hackathonRepository().update(h);
        unitOfWork.saveChanges();

        // adesso il team standard ha 3 persone -> 4 + 3 = 7 > 5
        Hackathon updated = controller.iscrizioneTeam(idTeam, idHackathon);
        Assert.assertNull(updated);
    }



    private Hackathon createHackathonIscrivibile() {
        Hackathon hackathon = new Hackathon();
        hackathon.setNome("Hack Iscrizione Test");
        hackathon.setArgomento("AI");
        hackathon.setDataInizio(LocalDate.now().plusDays(10));
        hackathon.setDataFine(LocalDate.now().plusDays(12));
        hackathon.setLuogo("Milano");
        hackathon.setPremio(5000.0);

        hackathon.setStato(State.IN_ISCRIZIONE);

        hackathon.setDimensioneMinimaTeam(2);
        hackathon.setDimensioneMassimaTeam(4);

        hackathon.setNumeroMassimoPersone(100);
        hackathon.setNumeroMinimoPersone(1);

        hackathon.setTeams(new ArrayList<>());

        return hackathon;
    }

    private Team createTeamConNMembri(int nMembri) {
        Team team = new Team();
        team.setNome("Team Test");
        team.setDataCreazione(LocalDate.now());

        // leader
        User leader = new User(100L, RuoloUser.UTENTE_NON_ISCRITTO);
        leader.setNome("Leader");
        leader.setCognome("Team");
        leader.setEmail("leader@esempio.it");
        team.setTeamLeader(leader);

        // membri
        List<User> membri = new ArrayList<>();
        for (int i = 0; i < nMembri; i++) {
            User u = new User((long) (200 + i), RuoloUser.UTENTE_NON_ISCRITTO);
            u.setNome("Membro" + i);
            u.setCognome("Team");
            u.setEmail("membro" + i + "@esempio.it");
            membri.add(u);
        }
        team.setMembriTeam(membri);

        return team;
    }

    private static class AlwaysValidHackathonValidator implements Validator<Hackathon> {
        @Override
        public boolean validate(Hackathon entity) {
            return true;
        }
    }
}
