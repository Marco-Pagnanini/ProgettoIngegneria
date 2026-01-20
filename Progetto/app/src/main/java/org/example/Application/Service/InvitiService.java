package org.example.Application.Service;

import org.example.Api.Models.Request.InvitoRequest;
import org.example.Application.Abstraction.Service.IInvitoService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.enums.RuoloUser;
import org.example.Core.enums.StatoInvito;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Invito;
import org.example.Core.models.Team;
import org.example.Core.models.User;
import org.example.utils.UnitOfWork.IUnitOfWork;

import java.util.List;

public class InvitiService implements IInvitoService {
    private IUnitOfWork unitOfWork;
    private Validator<Invito> validator;

    public InvitiService(IUnitOfWork unitOfWork, Validator<Invito> validator) {
        this.unitOfWork = unitOfWork;
        this.validator = validator;
    }


    @Override
    public Invito addInvito(InvitoRequest request) {

        Team team = unitOfWork.teamRepository().getById(request.getIdDelTeam());

        User user = unitOfWork.userRepository().getById(request.getIdUtente());

        Invito invito = new Invito();
        invito.setDalTeam(team);
        invito.setPerUtente(user);
        invito.setStato(request.getStato());
        invito.setDataInvito(request.getDataInvito());

        if(!validator.validate(invito)) return null;

        Invito response = unitOfWork.invitoRepository().create(invito);
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Invito updateInvito(Invito invito) {
        if(!validator.validate(invito)) return null;
        Invito response = unitOfWork.invitoRepository().update(invito);
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Invito deleteInvito(Long id) {
        Invito response = unitOfWork.invitoRepository().delete(id);
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public List<Invito> getAllInviti() {
        List<Invito> response = unitOfWork.invitoRepository().getAll();
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Invito getInvitoById(Long id) {
        Invito response = unitOfWork.invitoRepository().getById(id);
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Invito acceptInvito(Long idInvito) {
        Invito invito = unitOfWork.invitoRepository().getById(idInvito);
        invito.setStato(StatoInvito.ACCETTATO);

        //TODO logica per aggiungere al team l'utente
        //TODO logica per cambiare lo stato da Non Iscritto a Membro del team

        unitOfWork.invitoRepository().update(invito);
        unitOfWork.saveChanges();
        return invito;

    }
}
