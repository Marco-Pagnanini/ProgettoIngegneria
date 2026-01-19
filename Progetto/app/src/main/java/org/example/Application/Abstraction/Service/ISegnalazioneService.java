package org.example.Application.Abstraction.Service;

import org.example.Api.Models.Request.SegnalazioneRequest;
import org.example.Core.models.Segnalazione;

import java.util.List;

public interface ISegnalazioneService {
    Segnalazione addSegnalazione(Long idHackathon, SegnalazioneRequest segnalazione);
    Segnalazione deleteSegnalazione(Long idHackathon, Long idSegnalazione);
    Segnalazione getSegnalazioneById(Long idHackathon, Long idSegnalazione);
    List<Segnalazione> getAllSegnalazioniByHackathon(Long idHackathon);
}
