package org.example;

import org.example.Api.Controllers.HackathonController;
import org.example.Api.Controllers.SottoMissioneController;
import org.example.Api.Models.Request.HackathonRequest;
import org.example.Api.Models.Request.SottoMissioneRequest;
import org.example.Application.Service.HackathonService;
import org.example.Application.Service.SottoMissioniService;
import org.example.Application.Validator.HackathonValidator;
import org.example.Application.Validator.SottoMissioniValidator;
import org.example.Core.models.Hackathon;
import org.example.Core.models.SottoMissione;
import org.example.Infrastructure.Repository.*;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class VisualizzaSottoMissioniTest {

    private SottoMissioneController controller;
    private HackathonController hackathonController;
    private SottoMissioniService service;
    private HackathonService hackathonService;
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
                new ValutazioneRepository()
        );

        service = new SottoMissioniService(unitOfWork, new SottoMissioniValidator());
        controller = new SottoMissioneController(service);
        hackathonService = new HackathonService(unitOfWork, new HackathonValidator());
        hackathonController = new HackathonController(hackathonService);
    }

    @Test
    public void testVisualizzaSottoMissioni() {
        HackathonRequest hackathonRequest = createValidHackathonRequest();
        hackathonController.creazioneHackathon(hackathonRequest);

        Hackathon hackathon = hackathonController.visualizzaHackathonById(1L);
        Assert.assertNotNull("Hackathon non dovrebbe essere null", hackathon);

        SottoMissioneRequest sottoMissioneRequest = createSottoMissioneRequest();
        controller.aggiungiSottoMissione(hackathon.getId(), sottoMissioneRequest);

        List<SottoMissione> result = controller.visualisiSottoMissione(hackathon.getId());

        Assert.assertNotNull("La lista non dovrebbe essere null", result);
        Assert.assertEquals("Dovrebbe esserci 1 sotto-missione", 1, result.size());

        SottoMissione sottoMissione = result.get(0);
        Assert.assertEquals("Hack", sottoMissione.getTitolo());
        Assert.assertEquals("Descrizione della sotto-missione", sottoMissione.getDescrizione());
    }

    @Test
    public void testVisualizzaSottoMissioniListaVuota() {
        HackathonRequest hackathonRequest = createValidHackathonRequest();
        hackathonController.creazioneHackathon(hackathonRequest);

        Hackathon hackathon = hackathonController.visualizzaHackathonById(1L);

        List<SottoMissione> result = controller.visualisiSottoMissione(hackathon.getId());

        Assert.assertNotNull("La lista non dovrebbe essere null", result);
        Assert.assertTrue("La lista dovrebbe essere vuota", result.isEmpty());
    }

    @Test
    public void testVisualizzaMultipleSottoMissioni() {
        HackathonRequest hackathonRequest = createValidHackathonRequest();
        hackathonController.creazioneHackathon(hackathonRequest);
        Hackathon hackathon = hackathonController.visualizzaHackathonById(1L);

        SottoMissioneRequest request1 = new SottoMissioneRequest();
        request1.setTitolo("Missione 1");
        request1.setDescrizione("Prima missione");
        controller.aggiungiSottoMissione(hackathon.getId(), request1);

        SottoMissioneRequest request2 = new SottoMissioneRequest();
        request2.setTitolo("Missione 2");
        request2.setDescrizione("Seconda missione");
        controller.aggiungiSottoMissione(hackathon.getId(), request2);

        List<SottoMissione> result = controller.visualisiSottoMissione(hackathon.getId());

        Assert.assertEquals("Dovrebbero esserci 2 sotto-missioni", 2, result.size());
    }


    private HackathonRequest createValidHackathonRequest() {
        HackathonRequest request = new HackathonRequest();
        LocalDate oggi = LocalDate.now();

        request.setNome("Macerata AI");
        request.setRegolamento("Regolamento base per partecipazione");
        request.setArgomento("Intelligenza Artificiale");
        request.setScadenzaIscrizioni(oggi.plusDays(5));
        request.setDataInizio(oggi.plusDays(10));
        request.setDataFine(oggi.plusDays(12));
        request.setLuogo("Macerata");
        request.setPremio(5000.0);
        request.setDimensioneMinimaTeam(2);
        request.setDimensioneMassimaTeam(5);
        request.setNumeroMinimoPersone(20);
        request.setNumeroMassimoPersone(100);
        request.setGiudice(1L);
        request.setMentori(Arrays.asList(2L, 3L));

        return request;
    }

    private SottoMissioneRequest createSottoMissioneRequest() {
        SottoMissioneRequest request = new SottoMissioneRequest();
        request.setTitolo("Hack");
        request.setDescrizione("Descrizione della sotto-missione");
        return request;
    }
}