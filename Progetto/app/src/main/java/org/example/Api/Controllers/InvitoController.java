package org.example.Api.Controllers;

import org.example.Api.Models.Request.InvitoRequest;
import org.example.Application.Abstraction.Service.IInvitoService;
import org.example.Core.models.Invito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Invito>> getAllInviti() {
        List<Invito> inviti = invitoService.getAllInviti();
        return ResponseEntity.ok(inviti);
    }

    @GetMapping("/{idInvito}")
    public ResponseEntity<Invito> getInvitoById(@PathVariable Long idInvito) {
        Invito invito = invitoService.getInvitoById(idInvito);
        return ResponseEntity.ok(invito);
    }

    @PutMapping("/accetta/{idInvito}")
    public ResponseEntity<Invito> accettaInvito(@PathVariable Long idInvito) {
        Invito invito = invitoService.accettaInvito(idInvito);
        return ResponseEntity.ok(invito);
    }

    @PutMapping("/rifiuta/{idInvito}")
    public ResponseEntity<Invito> rifiutaInvito(@PathVariable Long idInvito) {
        Invito invito = invitoService.rifiutaInvito(idInvito);
        return ResponseEntity.ok(invito);
    }

    @PostMapping
    public ResponseEntity<Invito> creaInvito(@RequestBody InvitoRequest invitoRequest) {
        if (invitoRequest == null) {
            return ResponseEntity.badRequest().build();
        }
        Invito invito = invitoService.creaInvito(invitoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(invito);
    }

    @DeleteMapping("/{idInvito}")
    public ResponseEntity<Invito> deleteInvito(@PathVariable Long idInvito) {
        Invito invito = invitoService.deleteInvito(idInvito);
        return ResponseEntity.ok(invito);
    }
}
