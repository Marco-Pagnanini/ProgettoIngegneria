package org.example.Api.Controllers;


import org.example.Api.Models.Request.SupportoRequest;
import org.example.Application.Abstraction.Service.ISupportoService;
import org.example.Core.models.Supporto;
import org.example.Infrastructure.Service.Calendar.CallEventCalendar;
import org.example.Infrastructure.Service.Calendar.ScheduleCallRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/supporto")
public class SupportoController {

    private final ISupportoService supportoService;
    public SupportoController(ISupportoService supportoService) {
        this.supportoService = supportoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Supporto>> getSupportoById(@PathVariable Long idMentore) {
        return ResponseEntity.ok(supportoService.visualizzaSupporto(idMentore));
    }

    @PostMapping("/richiedi_supporto")
    public ResponseEntity<Supporto> addSupporto(@RequestBody SupportoRequest supporto) {
        return ResponseEntity.ok(supportoService.richiediSupporto(supporto));
    }

    @PostMapping("/crea_call/{id}")
    public ResponseEntity<CallEventCalendar> addCall(@PathVariable Long id, @RequestBody ScheduleCallRequest call) {
        return ResponseEntity.ok(supportoService.richiediCallEvent(id,call));
    }

}
