package org.example.Application.Service;

import org.example.Api.Exception.ResourceNotFoundException;
import org.example.Api.Exception.ValidationException;
import org.example.Api.Models.Mapper.SottoMissioneMapper;
import org.example.Api.Models.Request.SottoMissioneRequest;
import org.example.Application.Abstraction.Service.ISottoMissioniService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Hackathon;
import org.example.Core.models.SottoMissione;
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

        if(!validator.validate(sottoMissione)) {
            throw new ValidationException("Dati sottomissione non validi");
        }

        SottoMissione response = unitOfWork.sottoMissioneRepository().create(sottoMissione);

        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        if(hackathon == null) {
            throw new ResourceNotFoundException("Hackathon con id " + idHackathon + " non trovato");
        }

        hackathon.getSottoMissioni().add(response);
        unitOfWork.hackathonRepository().update(hackathon);
        unitOfWork.saveChanges();

        return response;
    }

    @Override
    public List<SottoMissione> visualizzaSottoMissione(Long hackathonId) {
        Hackathon hackathon = unitOfWork.hackathonRepository().getById(hackathonId);
        if(hackathon == null) {
            throw new ResourceNotFoundException("Hackathon con id " + hackathonId + " non trovato");
        }
        return hackathon.getSottoMissioni();
    }
}
