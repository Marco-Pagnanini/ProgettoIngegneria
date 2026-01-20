package org.example.Application.Abstraction.Service;

import org.example.Api.Models.Request.HackathonRequest;
import org.example.Core.models.Hackathon;

import java.util.List;

public interface IHackathonService {
    Hackathon creazioneHackathon(HackathonRequest hackathon);
    Hackathon updateHackathon(Hackathon hackathon);
    Hackathon deleteHackathon(Long id);
    Hackathon getHackathonById(Long id);
    List<Hackathon> visualizzaHackathon();

}
