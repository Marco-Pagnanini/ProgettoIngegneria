package org.example;

import org.example.Api.Controllers.HackathonController;
import org.example.Api.Models.Request.HackathonRequest;
import org.example.Application.Service.HackathonService;
import org.example.Application.Validator.HackathonValidator;
import org.example.Core.models.Hackathon;
import org.example.Infrastructure.Repository.HackathonRepository;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

public class CreazioneHackathonTest {
    private HackathonController controller;
    private HackathonValidator validator;

    @Before
    public void setUp() {
        validator = new HackathonValidator();
        controller = new HackathonController(
                new HackathonService(
                        new UnitOfWork(
                                new HackathonRepository()
                        ),
                        validator
                )
        );
    }

    @Test
    public void testCreazioneHackathonValido() {
        HackathonRequest request = createValidRequest();

        Hackathon response = controller.addHackathon(request);

        Assert.assertNotNull(response);
        Assert.assertEquals("Macerata AI", response.getNome());
        Assert.assertEquals("Intelligenza Artificiale", response.getArgomento());
    }

    @Test
    public void testDataFineAntecedenteDataInizio() {
        HackathonRequest request = createValidRequest();
        LocalDate oggi = LocalDate.now();
        request.setDataInizio(oggi.plusDays(5));
        request.setDataFine(oggi.plusDays(2));

        Hackathon response = controller.addHackathon(request);

        Assert.assertNull(response);
    }

    @Test
    public void testNumeroMassimoPersoneMinoreDelMinimo() {
        HackathonRequest request = createValidRequest();
        request.setNumeroMinimoPersone(50);
        request.setNumeroMassimoPersone(30);

        Hackathon response = controller.addHackathon(request);

        Assert.assertNull(response);
    }

    @Test
    public void testDimensioneTeamInvalida() {
        HackathonRequest request = createValidRequest();
        request.setDimensioneMinimaTeam(5);
        request.setDimensioneMassimaTeam(3);

        Hackathon response = controller.addHackathon(request);

        Assert.assertNull(response);
    }

    @Test
    public void testPremioNegativo() {
        HackathonRequest request = createValidRequest();
        request.setPremio(-1000.0);

        Hackathon response = controller.addHackathon(request);

        Assert.assertNull(response);
    }

    @Test
    public void testPremioZero() {
        HackathonRequest request = createValidRequest();
        request.setPremio(0.0);

        Hackathon response = controller.addHackathon(request);

        Assert.assertNotNull(response);
    }

    @Test
    public void testHackathonNull() {
        boolean isValid = validator.validate(null);

        Assert.assertFalse(isValid);
    }

    @Test
    public void testDateUguali() {
        HackathonRequest request = createValidRequest();
        LocalDate stessaData = LocalDate.now().plusDays(10);
        request.setDataInizio(stessaData);
        request.setDataFine(stessaData);

        Hackathon response = controller.addHackathon(request);

        Assert.assertNotNull(response);
    }

    @Test
    public void testScadenzaIscrizioniDopoDataInizio() {
        HackathonRequest request = createValidRequest();
        LocalDate oggi = LocalDate.now();
        request.setDataInizio(oggi.plusDays(10));
        request.setScadenzaIscrizioni(oggi.plusDays(15));

        Hackathon response = controller.addHackathon(request);

        Assert.assertNotNull(response);
    }

    @Test
    public void testDimensioniTeamMinimeUgualiAMassime() {
        HackathonRequest request = createValidRequest();
        request.setDimensioneMinimaTeam(5);
        request.setDimensioneMassimaTeam(5);

        Hackathon response = controller.addHackathon(request);

        Assert.assertNotNull(response);
    }

    @Test
    public void testNumeroPersoneMinimeUgualiAMassime() {
        HackathonRequest request = createValidRequest();
        request.setNumeroMinimoPersone(100);
        request.setNumeroMassimoPersone(100);

        Hackathon response = controller.addHackathon(request);

        Assert.assertNotNull(response);
    }

    @Test
    public void testCreazioneConUnSoloMentore() {
        HackathonRequest request = createValidRequest();
        request.setMentori(Collections.singletonList(2L));

        Hackathon response = controller.addHackathon(request);

        Assert.assertNotNull(response);
    }

    @Test
    public void testCreazioneConMultipliMentori() {
        HackathonRequest request = createValidRequest();
        request.setMentori(Arrays.asList(2L, 3L, 4L));

        Hackathon response = controller.addHackathon(request);

        Assert.assertNotNull(response);
    }

    @Test
    public void testPremioAlto() {
        HackathonRequest request = createValidRequest();
        request.setPremio(1000000.0);

        Hackathon response = controller.addHackathon(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(1000000.0, response.getPremio(), 0.01);
    }

    @Test
    public void testHackathonLungaDurata() {
        HackathonRequest request = createValidRequest();
        LocalDate oggi = LocalDate.now();
        request.setDataInizio(oggi.plusDays(30));
        request.setDataFine(oggi.plusDays(60));

        Hackathon response = controller.addHackathon(request);

        Assert.assertNotNull(response);
    }

    @Test
    public void testHackathonBrevedurata() {
        HackathonRequest request = createValidRequest();
        LocalDate oggi = LocalDate.now();
        request.setDataInizio(oggi.plusDays(10));
        request.setDataFine(oggi.plusDays(11));

        Hackathon response = controller.addHackathon(request);

        Assert.assertNotNull(response);
    }

    private HackathonRequest createValidRequest() {
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
}