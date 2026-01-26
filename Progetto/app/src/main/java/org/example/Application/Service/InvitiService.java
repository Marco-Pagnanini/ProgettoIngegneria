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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitiService implements IInvitoService {
    private IUnitOfWork unitOfWork;
    private Validator<Invito> validator;

    public InvitiService(IUnitOfWork unitOfWork, Validator<Invito> validator) {
        this.unitOfWork = unitOfWork;
        this.validator = validator;
    }


    @Override
    public Invito creaInvito(InvitoRequest request) {

        Team team = unitOfWork.teamRepository().getById(request.getIdDelTeam());

        User user = unitOfWork.userRepository().getById(request.getIdUtente());

        if(team == null || user == null) throw new IllegalArgumentException();

        Invito invito = new Invito();
        invito.setDalTeam(team);
        invito.setPerUtente(user);
        invito.setStato(request.getStato());
        invito.setDataInvito(request.getDataInvito());

        if(!validator.validate(invito)) throw new IllegalArgumentException();

        Invito response = unitOfWork.invitoRepository().create(invito);
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Invito updateInvito(Invito invito) {
        if(!validator.validate(invito)) throw new IllegalArgumentException();
        Invito response = unitOfWork.invitoRepository().update(invito);
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Invito deleteInvito(Long id) {
        Invito response = unitOfWork.invitoRepository().delete(id);
        if(response == null) throw new IllegalArgumentException();
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public List<Invito> getAllInviti() {
        List<Invito> response = unitOfWork.invitoRepository().getAll();
        if(response == null) throw new IllegalArgumentException();
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Invito getInvitoById(Long id) {
        Invito response = unitOfWork.invitoRepository().getById(id);
        if(response == null) throw new IllegalArgumentException();
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Invito accettaInvito(Long idInvito) {
        Invito invito = unitOfWork.invitoRepository().getById(idInvito);
        if(invito == null) throw new IllegalArgumentException();
        invito.setStato(StatoInvito.ACCETTATO);
        unitOfWork.invitoRepository().update(invito);

        User utente = invito.getPerUtente();
        Team team = invito.getDalTeam();

        if(utente == null || team == null) throw new IllegalArgumentException();

        //logica per cambiare lo stato da Non Iscritto a Membro del team

        utente.setRuolo(RuoloUser.TEAM_MEMBER);
        unitOfWork.userRepository().update(utente);

        // logica per aggiungere al team l'utente

        List<User> membri = unitOfWork.teamRepository().getById(team.getId()).getMembriTeam();
        if(membri == null) throw new IllegalArgumentException();
        membri.add(utente);

        team.setMembriTeam(membri);
        unitOfWork.teamRepository().update(team);


        unitOfWork.saveChanges();
        return invito;

    }

    public Invito rifiutaInvito(Long idInvito){
        Invito invito = unitOfWork.invitoRepository().getById(idInvito);
        if(invito == null) throw new IllegalArgumentException();
        invito.setStato(StatoInvito.RIFIUTATO);
        unitOfWork.invitoRepository().update(invito);

        unitOfWork.saveChanges();
        return invito;
    }
}
