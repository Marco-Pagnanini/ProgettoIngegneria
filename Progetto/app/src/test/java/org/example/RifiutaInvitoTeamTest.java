package org.example;

import org.example.Api.Controllers.InvitoController;
import org.example.Application.Service.InvitiService;
import org.example.Application.Validator.InvitoValidator;
import org.example.Core.enums.RuoloUser;
import org.example.Core.enums.StatoInvito;
import org.example.Core.models.Invito;
import org.example.Core.models.Team;
import org.example.Core.models.User;
import org.example.Infrastructure.Repository.*;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class RifiutaInvitoTeamTest {
    private InvitoController controller;
    private InvitiService service;
    private UnitOfWork unitOfWork;

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
                new ValutazioneRepository(),
                new UserStaffRepository()
        );
        service = new InvitiService(unitOfWork, new InvitoValidator());
        controller = new InvitoController(service);
    }

    @Test
    public void testInvitoTeam() {
        Invito invito = createAndSaveInvito();

        Invito response = controller.rifiutaInvito(invito.getId());

        Assert.assertEquals(response.getStato(),StatoInvito.RIFIUTATO);

    }

    private Invito createAndSaveInvito() {
        Team team = new Team();
        team.setNome("Team Test");

        User user = new User(1L, RuoloUser.UTENTE_NON_ISCRITTO);
        user.setNome("User Test");

        Invito invito = new Invito();
        invito.setDalTeam(team);
        invito.setPerUtente(user);
        invito.setStato(StatoInvito.PENDENTE);
        invito.setDataInvito(LocalDate.now());

        Invito saved = unitOfWork.invitoRepository().create(invito);
        unitOfWork.saveChanges();

        return saved;
    }
}
