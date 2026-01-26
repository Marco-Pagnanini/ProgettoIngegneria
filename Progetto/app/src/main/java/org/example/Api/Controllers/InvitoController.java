package org.example.Api.Controllers;

import org.example.Api.Models.Mapper.InvitoMapper;
import org.example.Api.Models.Request.InvitoRequest;
import org.example.Api.Models.Response.InvitoResponse;
import org.example.Application.Abstraction.Service.IInvitoService;
import org.example.Core.models.Invito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/invito")
public class InvitoController {
    private final IInvitoService invitoService;

    public InvitoController(IInvitoService invitoService) {
        this.invitoService = invitoService;
    }

    @GetMapping
    public ResponseEntity<List<InvitoResponse>> getAllInviti() {
        List<Invito> inviti = invitoService.getAllInviti();
        List<InvitoResponse> response = new ArrayList<>();
        for(Invito invito : inviti) {
            response.add(InvitoMapper.toResponse(invito));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idInvito}")
    public ResponseEntity<InvitoResponse> getInvitoById(@PathVariable Long idInvito) {
        Invito invito = invitoService.getInvitoById(idInvito);
        return ResponseEntity.ok(InvitoMapper.toResponse(invito));
    }

    @PutMapping("/accetta/{idInvito}")
    public ResponseEntity<InvitoResponse> accettaInvito(@PathVariable Long idInvito) {
        Invito invito = invitoService.accettaInvito(idInvito);
        return ResponseEntity.ok(InvitoMapper.toResponse(invito));
    }

    @PutMapping("/rifiuta/{idInvito}")
    public ResponseEntity<InvitoResponse> rifiutaInvito(@PathVariable Long idInvito) {
        Invito invito = invitoService.rifiutaInvito(idInvito);
        return ResponseEntity.ok(InvitoMapper.toResponse(invito));
    }

    @PostMapping
    public ResponseEntity<InvitoResponse> creaInvito(@RequestBody InvitoRequest invitoRequest) {
        if (invitoRequest == null) {
            return ResponseEntity.badRequest().build();
        }
        Invito invito = invitoService.creaInvito(invitoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(InvitoMapper.toResponse(invito));
    }

    @DeleteMapping("/{idInvito}")
    public ResponseEntity<InvitoResponse> deleteInvito(@PathVariable Long idInvito) {
        Invito invito = invitoService.deleteInvito(idInvito);
        return ResponseEntity.ok(InvitoMapper.toResponse(invito));
    }
}
