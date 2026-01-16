package org.example.Api.Controllers;

import org.example.Api.Models.Request.HackathonRequest;
import org.example.Application.Abstraction.Service.IHackathonService;
import org.example.Core.enums.RuoloStaff;
import org.example.Core.models.Hackathon;
import org.example.Core.models.UserStaff;
import org.example.utils.Builder.HackathonBuilderImplementation;

import java.util.ArrayList;
import java.util.List;

public class HackathonController {
    private final IHackathonService hackathonService;

    public HackathonController(IHackathonService hackathonService) {
        this.hackathonService = hackathonService;
    }

    public Hackathon addHackathon(HackathonRequest request) {
        //TODO: carica il Giudice con id request.idGiudice e mentori su request.idMentori
        UserStaff giudice = new UserStaff(request.getGiudice(), RuoloStaff.GIUDICE);
        List<UserStaff> mentori = new ArrayList<>();
        mentori.add(new UserStaff(request.getMentori().getFirst(),RuoloStaff.MENTORE));
        Hackathon hackathon = new HackathonBuilderImplementation()
                .nome(request.getNome())
                .regolamento(request.getRegolamento())
                .argomento(request.getArgomento())
                .scadenzaIscrizione(request.getScadenzaIscrizioni())
                .dataInizio(request.getDataInizio())
                .dataFine(request.getDataFine())
                .luogo(request.getLuogo())
                .premio(request.getPremio())
                .dimensioneMassimaTeam(request.getDimensioneMassimaTeam())
                .dimensioneMinimaTeam(request.getDimensioneMinimaTeam())
                .numeroMassimoPersone(request.getNumeroMassimoPersone())
                .numeroMinimoPersone(request.getNumeroMinimoPersone())
                .giudice(giudice)
                .mentori(mentori)
                .build();

        return hackathonService.addHackathon(hackathon);
    }

}
