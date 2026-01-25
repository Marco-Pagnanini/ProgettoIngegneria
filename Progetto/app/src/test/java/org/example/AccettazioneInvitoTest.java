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

public class AccettazioneInvitoTest {
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
    public void testAccettazioneInvitoValido() {
        Invito invito = createAndSaveInvito();

        invito.setStato(StatoInvito.ACCETTATO);
        Invito updated = service.updateInvito(invito);

        Assert.assertNotNull(updated);
        Assert.assertEquals(StatoInvito.ACCETTATO, updated.getStato());
    }

    @Test
    public void testAccettazioneInvitoPendente() {
        Invito invito = createAndSaveInvito();

        Assert.assertEquals(StatoInvito.PENDENTE, invito.getStato());

        invito.setStato(StatoInvito.ACCETTATO);
        Invito updated = service.updateInvito(invito);

        Assert.assertNotNull(updated);
        Assert.assertEquals(StatoInvito.ACCETTATO, updated.getStato());
    }

    @Test
    public void testAccettazioneInvitoInesistente() {
        Invito invito = new Invito();
        invito.setId(999L);
        invito.setStato(StatoInvito.ACCETTATO);

        Invito updated = service.updateInvito(invito);

        Assert.assertNull(updated);
    }


    @Test
    public void testAccettazioneMultipliInviti() {
        Invito invito1 = createAndSaveInvito();
        Invito invito2 = createAndSaveInvito();

        invito1.setStato(StatoInvito.ACCETTATO);
        invito2.setStato(StatoInvito.ACCETTATO);

        Invito updated1 = service.updateInvito(invito1);
        Invito updated2 = service.updateInvito(invito2);

        Assert.assertEquals(StatoInvito.ACCETTATO, updated1.getStato());
        Assert.assertEquals(StatoInvito.ACCETTATO, updated2.getStato());
    }

    @Test
    public void testAccettazioneInvitoVerificaStato() {
        Invito invito = createAndSaveInvito();
        Long idInvito = invito.getId();

        invito.setStato(StatoInvito.ACCETTATO);
        service.updateInvito(invito);

        Invito retrieved = service.getInvitoById(idInvito);

        Assert.assertNotNull(retrieved);
        Assert.assertEquals(StatoInvito.ACCETTATO, retrieved.getStato());
    }

    @Test
    public void testAccettazioneInvitoDataInvioNonCambia() {
        LocalDate dataOriginale = LocalDate.now();
        Invito invito = createAndSaveInvito();
        invito.setDataInvito(dataOriginale);
        unitOfWork.invitoRepository().update(invito);
        unitOfWork.saveChanges();

        invito.setStato(StatoInvito.ACCETTATO);
        Invito updated = service.updateInvito(invito);

        Assert.assertEquals(dataOriginale, updated.getDataInvito());
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