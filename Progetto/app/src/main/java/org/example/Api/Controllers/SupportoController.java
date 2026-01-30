package org.example.Api.Controllers;


import org.example.Api.Models.Request.SupportoRequest;
import org.example.Api.Models.Response.SupportoResponse;
import org.example.Application.Abstraction.Service.ISupportoService;
import org.example.Infrastructure.Service.Calendar.CallEventCalendar;
import org.example.Infrastructure.Service.Calendar.ScheduleCallRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('MENTORE')")
    public ResponseEntity<List<SupportoResponse>> getSupportoById(@PathVariable Long idMentore) {
        return ResponseEntity.ok(supportoService.visualizzaSupporto(idMentore));
    }

    @PostMapping("/richiedi_supporto")
    @PreAuthorize("hasRole('TEAM_LEADER')")
    public ResponseEntity<SupportoResponse> addSupporto(@RequestBody SupportoRequest supporto) {
        return ResponseEntity.ok(supportoService.richiediSupporto(supporto));
    }

    @PostMapping("/crea_call/{idSupporto}/{idMentore}")
    @PreAuthorize("hasRole('MENTORE')")
    public ResponseEntity<String> addCall(@PathVariable Long idMentore, @PathVariable Long idSupporto, @RequestBody ScheduleCallRequest call) {
        supportoService.richiediCallEvent(idSupporto,idMentore,call);
        return ResponseEntity.ok("Call Inviata"
                );
    }

}
