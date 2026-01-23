package org.example.Api.Controllers;


import org.example.Api.Models.Request.SottoMissioneRequest;
import org.example.Application.Abstraction.Service.ISottoMissioniService;
import org.example.Core.models.SottoMissione;

public class SottoMissioneController {
    private final ISottoMissioniService sottoMissioniService;

    public SottoMissioneController(ISottoMissioniService service) {
        this.sottoMissioniService = service;
    }

    public SottoMissione aggiungiSottoMissione(Long idHackathon, SottoMissioneRequest request){
        return sottoMissioniService.createSottoMissione(idHackathon, request);
    }
}
