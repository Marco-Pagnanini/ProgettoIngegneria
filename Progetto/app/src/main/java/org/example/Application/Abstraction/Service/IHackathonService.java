package org.example.Application.Abstraction.Service;

import org.example.Core.models.Hackathon;

import java.util.List;

public interface IHackathonService {
    Hackathon addHackathon(Hackathon hackathon);
    Hackathon updateHackathon(Hackathon hackathon);
    Hackathon deleteHackathon(Long id);
    Hackathon getHackathonById(Long id);
    List<Hackathon> getAllHackathon();

}
