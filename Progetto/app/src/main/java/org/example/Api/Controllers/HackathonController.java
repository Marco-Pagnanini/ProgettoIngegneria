package org.example.Api.Controllers;

import org.example.Api.Models.Request.HackathonRequest;
import org.example.Application.Abstraction.Service.IHackathonService;
import org.example.Core.enums.RuoloStaff;
import org.example.Core.models.Hackathon;
import org.example.Core.models.UserStaff;
import org.example.utils.Builder.HackathonBuilderImplementation;

import java.util.ArrayList;
import java.util.List;

public class HackathonController {
    private final IHackathonService hackathonService;

    public HackathonController(IHackathonService hackathonService) {
        this.hackathonService = hackathonService;
    }

    public Hackathon creazioneHackathon(HackathonRequest request) {
        return hackathonService.addHackathon(request);
    }

}
