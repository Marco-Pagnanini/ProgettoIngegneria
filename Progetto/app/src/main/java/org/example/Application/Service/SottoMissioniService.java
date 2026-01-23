package org.example.Application.Service;

import org.example.Api.Models.Mapper.SottoMissioneDomandaMapper;
import org.example.Api.Models.Mapper.SottoMissioneProgettoMapper;
import org.example.Api.Models.Request.sottomissione.SottoMissioneDomandaRequest;
import org.example.Api.Models.Request.sottomissione.SottoMissioneProgettoRequest;
import org.example.Application.Abstraction.Service.ISottoMissioniService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Hackathon;
import org.example.Core.models.sottoMissioni.SottoMissione;
import org.example.Core.models.sottoMissioni.SottoMissioneDomanda;
import org.example.Core.models.sottoMissioni.SottoMissioneProgetto;
import org.example.utils.UnitOfWork.IUnitOfWork;

public class SottoMissioniService implements ISottoMissioniService {

    private final IUnitOfWork unitOfWork;
    private final Validator<SottoMissione> validator;

    public SottoMissioniService(IUnitOfWork unitOfWork, Validator<SottoMissione> validator) {
        this.unitOfWork = unitOfWork;
        this.validator = validator;
    }

    @Override
    public SottoMissione createSottoMissioneDomanda(Long idHackathon, SottoMissioneDomandaRequest request) {

        SottoMissioneDomanda sottoMissione = SottoMissioneDomandaMapper.toEntity(request);

        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);

        sottoMissione.setHackathon(hackathon);

        if(!validator.validate(sottoMissione)) return null;

        hackathon.getSottoMissioni().add(sottoMissione);

        SottoMissione response = unitOfWork.sottoMissioneRepository().create(sottoMissione);

        unitOfWork.hackathonRepository().update(hackathon);

        unitOfWork.saveChanges();

        return response;

    }

    @Override
    public SottoMissione createSottoMissioneProgetto(Long idHackathon, SottoMissioneProgettoRequest request) {
        SottoMissioneProgetto sottoMissione = SottoMissioneProgettoMapper.toEntity(request);

        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);

        sottoMissione.setHackathon(hackathon);

        if(!validator.validate(sottoMissione)) return null;

        hackathon.getSottoMissioni().add(sottoMissione);

        SottoMissione response = unitOfWork.sottoMissioneRepository().create(sottoMissione);

        unitOfWork.hackathonRepository().update(hackathon);

        unitOfWork.saveChanges();

        return response;
    }
}
