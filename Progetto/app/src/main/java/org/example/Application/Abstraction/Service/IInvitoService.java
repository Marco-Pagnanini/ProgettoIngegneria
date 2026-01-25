package org.example.Application.Abstraction.Service;

import org.example.Api.Models.Request.InvitoRequest;
import org.example.Core.models.Invito;

import java.util.List;

public interface IInvitoService {
    Invito creaInvito(InvitoRequest invito);
    Invito updateInvito(Invito invito);
    Invito deleteInvito(Long id);
    List<Invito> getAllInviti();
    Invito getInvitoById(Long id);
    Invito accettaInvito(Long idInvito);
    Invito rifiutaInvito(Long idInvito);
}
