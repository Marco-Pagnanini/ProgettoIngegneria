package org.example.Api.Controllers;

import org.example.Api.Models.Request.ValutazioneRequest;
import org.example.Application.Abstraction.Service.IValutazioniService;
import org.example.Core.models.Valutazione;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/valutazioni")
public class ValutazioneController {
    private final IValutazioniService valutazioniService;

    public ValutazioneController(IValutazioniService valutazioniService) {
        this.valutazioniService = valutazioniService;
    }

    @PostMapping
    public ResponseEntity<Valutazione> valutaSottoMissione(@RequestBody ValutazioneRequest request) {
        if (request == null || request.getIdRisposta() == null || request.getPunteggio() == null) {
            return ResponseEntity.badRequest().build();
        }
        Valutazione valutazione = valutazioniService.valutaSottoMissione(
                request.getIdRisposta(),
                request.getPunteggio(),
                request.getTesto()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(valutazione);
    }
}
