package org.example;

import org.example.Api.Controllers.HackathonController;
import org.example.Api.Models.Request.sottomissione.SottoMissioneDomandaRequest;
import org.example.Api.Models.Request.sottomissione.SottoMissioneProgettoRequest;
import org.example.Application.Service.HackathonService;
import org.example.Application.Service.SottoMissioniService;
import org.example.Application.Validator.HackathonValidator;
import org.example.Application.Validator.SottoMissioniValidator;
import org.example.Core.enums.State;
import org.example.Core.models.Hackathon;
import org.example.Core.models.sottoMissioni.SottoMissione;
import org.example.Core.models.sottoMissioni.SottoMissioneDomanda;
import org.example.Core.models.sottoMissioni.SottoMissioneProgetto;
import org.example.Infrastructure.Repository.*;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class SottoMissioneTest {
    private HackathonController controller;
    private SottoMissioniService sottoMissioniService;
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
                new SottoMissioniRepository()
        );

        hackathonService = new HackathonService(unitOfWork, new HackathonValidator());
        sottoMissioniService = new SottoMissioniService(unitOfWork, new SottoMissioniValidator());
        controller = new HackathonController(hackathonService, sottoMissioniService);
    }

    // ========== TEST CREAZIONE DOMANDA APERTA ==========

    @Test
    public void testCreazioneSottoMissioneDomandaValida() {
        // Arrange
        Hackathon hackathon = createAndSaveHackathon();
        SottoMissioneDomandaRequest request = createDomandaRequest();

        // Act
        SottoMissione result = controller.aggiungiSottoMissioneDomanda(hackathon.getId(), request);

        // Assert
        Assert.assertNotNull(result);
        Assert.assertTrue(result instanceof SottoMissioneDomanda);
        Assert.assertEquals("Descrivi il tuo approccio", result.getTitolo());
        Assert.assertEquals(hackathon.getId(), result.getHackathon().getId());
    }

    @Test
    public void testCreazioneSottoMissioneDomandaConRisposta() {
        // Arrange
        Hackathon hackathon = createAndSaveHackathon();
        SottoMissioneDomandaRequest request = createDomandaRequest();
        request.setRisposta("La mia risposta dettagliata");

        // Act
        SottoMissione result = controller.aggiungiSottoMissioneDomanda(hackathon.getId(), request);

        // Assert
        Assert.assertNotNull(result);
        SottoMissioneDomanda domanda = (SottoMissioneDomanda) result;
        Assert.assertEquals("La mia risposta dettagliata", domanda.getRisposta());
    }

    @Test
    public void testCreazioneSottoMissioneDomandaHackathonInesistente() {
        // Arrange
        SottoMissioneDomandaRequest request = createDomandaRequest();

        // Act
        SottoMissione result = controller.aggiungiSottoMissioneDomanda(999L, request);

        // Assert
        Assert.assertNull(result);
    }

    // ========== TEST CREAZIONE PROGETTO ==========

    @Test
    public void testCreazioneSottoMissioneProgettoValido() {
        // Arrange
        Hackathon hackathon = createAndSaveHackathon();
        SottoMissioneProgettoRequest request = createProgettoRequest();

        // Act
        SottoMissione result = controller.aggiungiSottoMissioneProgetto(hackathon.getId(), request);

        // Assert
        Assert.assertNotNull(result);
        Assert.assertTrue(result instanceof SottoMissioneProgetto);
        Assert.assertEquals("Sviluppa un'app", result.getTitolo());
    }

    @Test
    public void testCreazioneSottoMissioneProgettoConUrl() {
        // Arrange
        Hackathon hackathon = createAndSaveHackathon();
        SottoMissioneProgettoRequest request = createProgettoRequest();
        request.setUrlProgetto("https://github.com/user/repo");

        // Act
        SottoMissione result = controller.aggiungiSottoMissioneProgetto(hackathon.getId(), request);

        // Assert
        Assert.assertNotNull(result);
        SottoMissioneProgetto progetto = (SottoMissioneProgetto) result;
        Assert.assertEquals("https://github.com/user/repo", progetto.getUrlProgetto());
    }

    @Test
    public void testCreazioneSottoMissioneProgettoHackathonInesistente() {
        // Arrange
        SottoMissioneProgettoRequest request = createProgettoRequest();

        // Act
        SottoMissione result = controller.aggiungiSottoMissioneProgetto(999L, request);

        // Assert
        Assert.assertNull(result);
    }

    // ========== TEST RELAZIONE HACKATHON-SOTTOMISSIONE ==========

    @Test
    public void testSottoMissioneAggiuntaAdHackathon() {
        // Arrange
        Hackathon hackathon = createAndSaveHackathon();
        SottoMissioneDomandaRequest request = createDomandaRequest();

        // Act
        SottoMissione sottoMissione = controller.aggiungiSottoMissioneDomanda(hackathon.getId(), request);

        // Assert
        Hackathon updated = unitOfWork.hackathonRepository().getById(hackathon.getId());
        Assert.assertEquals(1, updated.getSottoMissioni().size());
        Assert.assertEquals(sottoMissione.getId(), updated.getSottoMissioni().get(0).getId());
    }

    @Test
    public void testMultipleSottoMissioniPerHackathon() {
        // Arrange
        Hackathon hackathon = createAndSaveHackathon();

        // Act
        SottoMissione domanda = controller.aggiungiSottoMissioneDomanda(
                hackathon.getId(),
                createDomandaRequest()
        );
        SottoMissione progetto = controller.aggiungiSottoMissioneProgetto(
                hackathon.getId(),
                createProgettoRequest()
        );

        // Assert
        Hackathon updated = unitOfWork.hackathonRepository().getById(hackathon.getId());
        Assert.assertEquals(2, updated.getSottoMissioni().size());
    }

    @Test
    public void testRelazioneBidirezionale() {
        // Arrange
        Hackathon hackathon = createAndSaveHackathon();
        SottoMissioneDomandaRequest request = createDomandaRequest();

        // Act
        SottoMissione sottoMissione = controller.aggiungiSottoMissioneDomanda(hackathon.getId(), request);

        // Assert - Verifica entrambi i lati della relazione
        Assert.assertNotNull(sottoMissione.getHackathon());
        Assert.assertEquals(hackathon.getId(), sottoMissione.getHackathon().getId());

        Hackathon updated = unitOfWork.hackathonRepository().getById(hackathon.getId());
        Assert.assertTrue(updated.getSottoMissioni().contains(sottoMissione));
    }

    // ========== TEST PERSISTENZA ==========

    @Test
    public void testSottoMissionePersistita() {
        // Arrange
        Hackathon hackathon = createAndSaveHackathon();
        SottoMissioneDomandaRequest request = createDomandaRequest();

        // Act
        SottoMissione created = controller.aggiungiSottoMissioneDomanda(hackathon.getId(), request);
        Long id = created.getId();

        // Assert - Recupera dal repository
        SottoMissione retrieved = unitOfWork.sottoMissioneRepository().getById(id);
        Assert.assertNotNull(retrieved);
        Assert.assertEquals(created.getTitolo(), retrieved.getTitolo());
    }

    @Test
    public void testDatiSottoMissioneDomandaPersistiti() {
        // Arrange
        Hackathon hackathon = createAndSaveHackathon();
        SottoMissioneDomandaRequest request = createDomandaRequest();
        request.setRisposta("Risposta test");

        // Act
        SottoMissione created = controller.aggiungiSottoMissioneDomanda(hackathon.getId(), request);

        // Assert
        SottoMissioneDomanda retrieved = (SottoMissioneDomanda)
                unitOfWork.sottoMissioneRepository().getById(created.getId());
        Assert.assertEquals("Risposta test", retrieved.getRisposta());
    }

    @Test
    public void testDatiSottoMissioneProgettoPersistiti() {
        // Arrange
        Hackathon hackathon = createAndSaveHackathon();
        SottoMissioneProgettoRequest request = createProgettoRequest();
        request.setUrlProgetto("https://github.com/test/repo");

        // Act
        SottoMissione created = controller.aggiungiSottoMissioneProgetto(hackathon.getId(), request);

        // Assert
        SottoMissioneProgetto retrieved = (SottoMissioneProgetto)
                unitOfWork.sottoMissioneRepository().getById(created.getId());
        Assert.assertEquals("https://github.com/test/repo", retrieved.getUrlProgetto());
    }

    // ========== HELPER METHODS ==========

    private Hackathon createAndSaveHackathon() {
        Hackathon hackathon = new Hackathon();
        hackathon.setNome("AI Challenge 2025");
        hackathon.setRegolamento("Regole del contest");
        hackathon.setDataInizio(LocalDate.now().plusDays(10));
        hackathon.setDataFine(LocalDate.now().plusDays(12));
        hackathon.setStato(State.IN_ISCRIZIONE);

        Hackathon saved = unitOfWork.hackathonRepository().create(hackathon);
        unitOfWork.saveChanges();
        return saved;
    }

    private SottoMissioneDomandaRequest createDomandaRequest() {
        SottoMissioneDomandaRequest request = new SottoMissioneDomandaRequest();
        request.setTitolo("Descrivi il tuo approccio");
        request.setDescrizione("Spiega in dettaglio come risolveresti il problema");
        return request;
    }

    private SottoMissioneProgettoRequest createProgettoRequest() {
        SottoMissioneProgettoRequest request = new SottoMissioneProgettoRequest();
        request.setTitolo("Sviluppa un'app");
        request.setDescrizione("Crea un'applicazione mobile per la gestione task");
        return request;
    }
}