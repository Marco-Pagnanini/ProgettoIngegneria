package org.example;

import org.example.Api.Controllers.HackathonController;
import org.example.Api.Controllers.SottoMissioneController;
import org.example.Api.Models.Request.HackathonRequest;
import org.example.Application.Service.HackathonService;
import org.example.Application.Service.SottoMissioniService;
import org.example.Application.Validator.HackathonValidator;
import org.example.Application.Validator.SottoMissioniValidator;
import org.example.Core.models.Hackathon;
import org.example.Core.models.SottoMissione;
import org.example.Core.models.User;
import org.example.Infrastructure.Repository.*;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisualiaSottoMissione {
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

        // Se non hai validator per Hackathon, puoi passare null o rimuoverlo dal service
        service = new SottoMissioniService(unitOfWork, new SottoMissioniValidator());
        controller = new SottoMissioneController(service);
        hackathonService = new HackathonService(unitOfWork, new HackathonValidator());
        hackathonController = new HackathonController(hackathonService);

    }
    


    private List<SottoMissione> createSottoMissione() {
        List<SottoMissione> sottoMissione = new ArrayList<>();
        SottoMissione sottoMissione1 = new SottoMissione();
        sottoMissione1.setId(1L);
        sottoMissione1.setTitolo("Hack");
        sottoMissione1.setDescrizione("...");
        sottoMissione.add(sottoMissione1);
        return sottoMissione;
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
