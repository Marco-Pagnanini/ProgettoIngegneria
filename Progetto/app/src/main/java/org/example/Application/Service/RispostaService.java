package org.example.Application.Service;

import org.example.Api.Models.Mapper.RispostaMapper;
import org.example.Api.Models.Request.RispostaRequest;
import org.example.Application.Abstraction.Service.IRispostaService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Risposta;
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.springframework.stereotype.Service;

@Service
public class RispostaService implements IRispostaService {

    private final IUnitOfWork unitOfWork;
    private Validator<Risposta> validator;

    public RispostaService(IUnitOfWork unitOfWork, Validator<Risposta> validator) {
        this.unitOfWork = unitOfWork;
        this.validator = validator;
    }
    @Override
    public Risposta inviaRisposta(RispostaRequest request) {

        Risposta risposta = new RispostaMapper(unitOfWork).toEntity(request);

        if(!validator.validate(risposta)) {
           throw new IllegalArgumentException();
        }

        unitOfWork.rispostaRepository().create(risposta);
        unitOfWork.saveChanges();
        return risposta;
    }

    @Override
    public Risposta aggiornaRisposta(RispostaRequest request) {

        if (request == null || request.getIdRisposta() == null || request.getRisposta() == null) throw new IllegalArgumentException();
        Risposta risposta = unitOfWork.rispostaRepository().getById(request.getIdRisposta());
        if(risposta == null) throw new IllegalArgumentException();
        risposta.setTesto(request.getRisposta());

        if (!validator.validate(risposta)) throw new IllegalArgumentException();

        Risposta aggiornata =  unitOfWork.rispostaRepository().update(risposta);
        unitOfWork.saveChanges();
        return aggiornata;
    }
}
