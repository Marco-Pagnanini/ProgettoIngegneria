package org.example.utils.UnitOfWork;

import org.example.Application.Abstraction.Repository.*;
import org.example.Application.Abstraction.Service.IRispostaService;
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
    private IRispostaRepository rispostaRepository;
    private IValutazioneRepository valutazioneRepository;

    public UnitOfWork(IHackathonRepository hackathonRepository,
                      ITeamRepository teamRepository,
                      IInvitoRepository invitoRepository,
                      ISegnalazioneRepository segnalazioneRepository,
                      IUserRepository userRepository,
                      ISottoMissioneRepository sottoMissioneRepository,
                      IRispostaRepository rispostaRepository,
                      IValutazioneRepository valutazioneRepository

    ) {
        this.hackathonRepository = hackathonRepository;
        this.teamRepository = teamRepository;
        this.invitoRepository = invitoRepository;
        this.segnalazioneRepository = segnalazioneRepository;
        this.userRepository = userRepository;
        this.sottoMissioneRepository = sottoMissioneRepository;
        this.rispostaRepository = rispostaRepository;
        this.valutazioneRepository = valutazioneRepository;
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
    public IRispostaRepository rispostaRepository() { return rispostaRepository;}
    public IValutazioneRepository valutazioneRepository() { return valutazioneRepository;}

    @Override
    public ISottoMissioneRepository sottoMissioneRepository() {
        return sottoMissioneRepository;
    }

    @Override
    public void saveChanges() {
        //TODO TRANSACTION
    }
}
