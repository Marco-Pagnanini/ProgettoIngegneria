package org.example.Api.Controllers;

import org.example.Api.Models.Request.InvitoRequest;
import org.example.Application.Abstraction.Service.IInvitoService;
import org.example.Core.models.Invito;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/invito")
public class InvitoController {
    private final IInvitoService invitoService;

    public InvitoController(IInvitoService invitoService) {
        this.invitoService = invitoService;
    }

    @GetMapping
    public List<Invito> getAllInviti(){
        return invitoService.getAllInviti();
    }

    @PutMapping("/accetta/{idInvito}")
    public Invito accettaInvito(Long idInvito){
        return invitoService.accettaInvito(idInvito);
    }

    @PutMapping("/rifiuta/{idInvito}")
    public Invito rifiutaInvito(Long idInvito){
        return invitoService.rifiutaInvito(idInvito);
    }

    @PostMapping
    public Invito creaInvito(@RequestBody InvitoRequest invitoRequest){
        return invitoService.creaInvito(invitoRequest);
    }

}
