package org.example.utils.UnitOfWork;

import org.example.Application.Abstraction.Repository.*;
import org.example.Application.Abstraction.Service.ISottoMissioniService;
import org.example.Core.models.Invito;
import org.example.Infrastructure.Repository.InvitoRepository;

public class UnitOfWork implements IUnitOfWork{

    private IHackathonRepository hackathonRepository;
    private ITeamRepository teamRepository;
    private IInvitoRepository invitoRepository;
    private ISegnalazioneRepository segnalazioneRepository;
    private IUserRepository  userRepository;
    private ISottoMissioneRepository sottoMissioneRepository;

    public UnitOfWork(IHackathonRepository hackathonRepository,
                      ITeamRepository teamRepository,
                      IInvitoRepository invitoRepository,
                      ISegnalazioneRepository segnalazioneRepository,
                      IUserRepository userRepository,
                      ISottoMissioneRepository sottoMissioneRepository) {
        this.hackathonRepository = hackathonRepository;
        this.teamRepository = teamRepository;
        this.invitoRepository = invitoRepository;
        this.segnalazioneRepository = segnalazioneRepository;
        this.userRepository = userRepository;
        this.sottoMissioneRepository = sottoMissioneRepository;
    }

    public IHackathonRepository hackathonRepository() {
        return hackathonRepository;
    }
    public ITeamRepository teamRepository() {
        return teamRepository;
    }
    public IInvitoRepository invitoRepository() {return  invitoRepository;}
    public ISegnalazioneRepository segnalazioneRepository() { return segnalazioneRepository;}
    public IUserRepository userRepository() {return userRepository;}

    @Override
    public ISottoMissioneRepository sottoMissioneRepository() {
        return sottoMissioneRepository;
    }

    @Override
    public void saveChanges() {
        //TODO TRANSACTION
    }
}
