package org.example.Api.Controllers;

import org.example.Application.Abstraction.Service.IInvitoService;
import org.example.Core.models.Invito;
import org.example.Infrastructure.Repository.InvitoRepository;

import java.util.List;

public class InvitoController {
    private IInvitoService invitoService;

    public InvitoController(IInvitoService invitoService) {
        this.invitoService = invitoService;
    }

    public List<Invito> getAllInviti(){
        return invitoService.getAllInviti();
    }

    public Invito acceptInvitation(Long idInvito){
        return invitoService.acceptInvito(idInvito);
    }

}
