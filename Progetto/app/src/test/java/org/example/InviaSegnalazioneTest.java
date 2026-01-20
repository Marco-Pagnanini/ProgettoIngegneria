package org.example;

import org.example.Api.Controllers.SegnalazioneController;
import org.example.Application.Service.SegnalazioneService;
import org.example.Application.Validator.SegnalazioneValidator;
import org.example.Core.models.Hackathon;
import org.example.Infrastructure.Repository.HackathonRepository;
import org.example.Infrastructure.Repository.InvitoRepository;
import org.example.Infrastructure.Repository.TeamRepository;
import org.example.Infrastructure.Repository.UserRepository;
import org.example.utils.UnitOfWork.UnitOfWork;
import org.junit.Before;

public class InviaSegnalazioneTest {
    private SegnalazioneController segnalazioneController;
    private SegnalazioneService segnalazioneService;
    private UnitOfWork unitOfWork;

    private Long idHackathon;
    private Long idTeam;

    @Before
    public void setUp() {
        unitOfWork = new UnitOfWork(new HackathonRepository(),
                new TeamRepository(),
                new InvitoRepository(),
                new UserRepository());

        segnalazioneService = new SegnalazioneService(unitOfWork,  new SegnalazioneValidator());
        segnalazioneController =  new SegnalazioneController(segnalazioneService);

        Hackathon
    }
}
