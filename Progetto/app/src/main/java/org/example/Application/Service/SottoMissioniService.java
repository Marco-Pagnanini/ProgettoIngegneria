package org.example.Application.Service;

import org.example.Api.Models.Mapper.SottoMissioneMapper;
import org.example.Api.Models.Request.SottoMissioneRequest;
import org.example.Application.Abstraction.Service.ISottoMissioniService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Application.Validator.SottoMissioniValidator;
import org.example.Core.models.Hackathon;
import org.example.Core.models.SottoMissione;
import org.example.utils.UnitOfWork.IUnitOfWork;

public class SottoMissioniService implements ISottoMissioniService {

    private final IUnitOfWork unitOfWork;
    private final Validator<SottoMissione> validator;

    public SottoMissioniService(IUnitOfWork unitOfWork, Validator<SottoMissione> validator) {
        this.unitOfWork = unitOfWork;
        this.validator = validator;
    }

    @Override
    public SottoMissione createSottoMissione(Long idHackathon, SottoMissioneRequest request) {

        SottoMissione sottoMissione = SottoMissioneMapper.toEntity(request);

        if(!validator.validate(sottoMissione)) return null;

        SottoMissione response = unitOfWork.sottoMissioneRepository().create(sottoMissione);

        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);

        hackathon.getSottoMissioni().add(response);

        unitOfWork.hackathonRepository().update(hackathon);

        unitOfWork.saveChanges();

        return response;

    }
}
