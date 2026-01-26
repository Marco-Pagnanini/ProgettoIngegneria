package org.example;

import org.example.Api.Models.Request.RispostaRequest;
import org.example.Application.Service.RispostaService;
import org.example.Application.Validator.RispostaValidator;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Risposta;
import org.example.Core.models.SottoMissione;
import org.example.Core.models.Team;
import org.example.Infrastructure.Repository.*;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AggiornaRispostaSottomissioneTest {
    private RispostaService service;
    private UnitOfWork unitOfWork;
    private Long idRisposta;

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

        service = new RispostaService(unitOfWork, new RispostaValidator());

        Team team = createTestTeam(10L, "Team A");
        Hackathon hackathon = createTestHackathon(1L, "Hackathon A");
        SottoMissione sm = createTestSottoMissione(100L);

        Risposta r = new Risposta();
        r.setTesto("Testo iniziale");
        r.setTeam(team);
        r.setHackathon(hackathon);
        r.setSottoMissione(sm);
        r.setDataInvio(LocalDateTime.now());

        Risposta created = unitOfWork.rispostaRepository().create(r);
        unitOfWork.saveChanges();
        idRisposta = created.getId();
    }

    @Test
    public void testAggiornaRispostaSuccesso() {
        RispostaRequest req = new RispostaRequest();
        req.setIdRisposta(idRisposta);
        req.setRisposta("Testo aggiornato");

        Risposta updated = service.aggiornaRisposta(req);

        Assert.assertNotNull(updated);
        Assert.assertEquals("Testo aggiornato", updated.getTesto());
    }

    @Test
    public void testAggiornaRispostaTestoNonValido() {
        RispostaRequest req = new RispostaRequest();
        req.setIdRisposta(idRisposta);
        req.setRisposta("   ");

        Risposta updated = service.aggiornaRisposta(req);

        Assert.assertNull(updated);
    }

    @Test
    public void testAggiornaRispostaIdInesistente() {
        RispostaRequest req = new RispostaRequest();
        req.setIdRisposta(999L);
        req.setRisposta("Nuovo testo");

        Risposta updated = service.aggiornaRisposta(req);

        Assert.assertNull(updated);
    }

    private Team createTestTeam(Long id, String nome) {
        Team t = new Team();
        t.setId(id);
        t.setNome(nome);
        t.setDataCreazione(LocalDate.now());
        return t;
    }

    private Hackathon createTestHackathon(Long id, String nome) {
        Hackathon h = new Hackathon();
        h.setId(id);
        h.setNome(nome);
        h.setArgomento("AI");
        h.setDataInizio(LocalDate.now().plusDays(10));
        h.setDataFine(LocalDate.now().plusDays(12));
        h.setLuogo("Milano");
        h.setPremio(1000.0);
        return h;
    }

    private SottoMissione createTestSottoMissione(Long id) {
        SottoMissione sm = new SottoMissione();
        sm.setId(id);
        return sm;
    }
}
