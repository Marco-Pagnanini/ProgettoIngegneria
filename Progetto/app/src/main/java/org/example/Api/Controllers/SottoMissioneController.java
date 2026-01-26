package org.example.Api.Controllers;


import org.example.Api.Models.Request.SottoMissioneRequest;
import org.example.Application.Abstraction.Service.ISottoMissioniService;
import org.example.Core.models.SottoMissione;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sottomissione")
public class SottoMissioneController {
    private final ISottoMissioniService sottoMissioniService;

    public SottoMissioneController(ISottoMissioniService service) {
        this.sottoMissioniService = service;
    }

    @PostMapping
    public SottoMissione aggiungiSottoMissione(@RequestBody Long idHackathon,@RequestBody SottoMissioneRequest request){
        return sottoMissioniService.createSottoMissione(idHackathon, request);
    }

    @GetMapping("/{idHackathon}")
    public List<SottoMissione> visualisiSottoMissione(@PathVariable Long idHackathon){
        return sottoMissioniService.visualizzaSottoMissione(idHackathon);
    }
}
