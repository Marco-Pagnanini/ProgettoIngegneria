package org.example.Api.Controllers;

import org.example.Api.Models.Request.InvitoRequest;
import org.example.Application.Abstraction.Service.IInvitoService;
import org.example.Core.models.Invito;

import java.util.List;

public class InvitoController {
    private final IInvitoService invitoService;


    public InvitoController(IInvitoService invitoService) {
        this.invitoService = invitoService;
    }

    public List<Invito> getAllInviti(){
        return invitoService.getAllInviti();
    }

    public Invito accettaInvito(Long idInvito){
        return invitoService.accettaInvito(idInvito);
    }

    public Invito creaInvito(InvitoRequest invitoRequest){
        return invitoService.creaInvito(invitoRequest);
    }

}
