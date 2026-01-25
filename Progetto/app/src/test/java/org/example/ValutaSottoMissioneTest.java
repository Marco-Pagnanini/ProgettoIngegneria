package org.example;

import org.example.Api.Controllers.ValutazioneController;
import org.example.Application.Service.ValutazioneService;
import org.example.Application.Validator.ValutazioneValidator;
import org.example.Core.models.Risposta;
import org.example.Core.models.Valutazione;
import org.example.Infrastructure.Repository.*;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValutaSottoMissioneTest {

    private ValutazioneController controller;
    private ValutazioneService service;
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

        service = new ValutazioneService(unitOfWork, new ValutazioneValidator());
        controller = new ValutazioneController(service);
    }

    @Test
    public void testValutaSottoMissioneConSuccesso() {
        Risposta risposta = createRisposta();
        unitOfWork.rispostaRepository().create(risposta);
        unitOfWork.saveChanges();

        Valutazione result = controller.valutaSottoMissione(1L, 85, "Ottimo lavoro");

        Assert.assertNotNull(result);
        Assert.assertEquals(Integer.valueOf(85), result.getPunteggio());
        Assert.assertEquals("Ottimo lavoro", result.getGiudizio());
        Assert.assertNotNull(result.getRisposta());
    }

    @Test
    public void testValutaSottoMissionePunteggioMassimo() {
        Risposta risposta = createRisposta();
        unitOfWork.rispostaRepository().create(risposta);
        unitOfWork.saveChanges();

        Valutazione result = controller.valutaSottoMissione(1L, 100, "Perfetto");

        Assert.assertNotNull(result);
        Assert.assertEquals(Integer.valueOf(100), result.getPunteggio());
        Assert.assertEquals("Perfetto", result.getGiudizio());
    }

    @Test
    public void testValutaSottoMissionePunteggioMinimo() {
        Risposta risposta = createRisposta();
        unitOfWork.rispostaRepository().create(risposta);
        unitOfWork.saveChanges();

        Valutazione result = controller.valutaSottoMissione(1L, 0, "Insufficiente");

        Assert.assertNotNull(result);
        Assert.assertEquals(Integer.valueOf(0), result.getPunteggio());
        Assert.assertEquals("Insufficiente", result.getGiudizio());
    }

    @Test
    public void testValutaSottoMissioneRispostaNonTrovata() {
        Valutazione result = controller.valutaSottoMissione(9999L, 50, "Test");

        Assert.assertNotNull(result);
        Assert.assertNull(result.getRisposta());
    }

    @Test
    public void testValutaSottoMissioneTestoVuoto() {
        Risposta risposta = createRisposta();
        unitOfWork.rispostaRepository().create(risposta);
        unitOfWork.saveChanges();

        Valutazione result = controller.valutaSottoMissione(1L, 70, "");

        Assert.assertNull(result);
    }

    private Risposta createRisposta() {
        Risposta risposta = new Risposta();
        risposta.setId(1L);
        risposta.setTesto("Questa è la mia risposta alla sotto-missione");
        return risposta;
    }
}