package org.example.Api.Models.Mapper;

import org.example.Api.Models.Request.RispostaRequest;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Risposta;
import org.example.Core.models.SottoMissione;
import org.example.Core.models.Team;
import org.example.utils.UnitOfWork.IUnitOfWork;

import java.time.LocalDateTime;

public class RispostaMapper {
    private final IUnitOfWork unitOfWork;

    public RispostaMapper(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public Risposta toEntity(RispostaRequest request) {
        Risposta risposta = new Risposta();

        Hackathon hackathon = unitOfWork.hackathonRepository().getById(request.getIdHackathon());
        Team team = unitOfWork.teamRepository().getById(request.getIdTeam());
        SottoMissione sottoMissione = unitOfWork.sottoMissioneRepository().getById(request.getIdSottomissione());
        risposta.setTesto(request.getRisposta());
        risposta.setHackathon(hackathon);
        risposta.setTeam(team);
        risposta.setSottoMissione(sottoMissione);
        risposta.setDataInvio(LocalDateTime.now());

        return risposta;

    }
}
