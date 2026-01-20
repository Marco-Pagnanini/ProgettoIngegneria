package org.example.utils.UnitOfWork;

import org.example.Application.Abstraction.Repository.IHackathonRepository;
import org.example.Application.Abstraction.Repository.IInvitoRepository;
import org.example.Application.Abstraction.Repository.ISegnalazioneRepository;
import org.example.Application.Abstraction.Repository.ITeamRepository;
import org.example.Core.models.Invito;
import org.example.Infrastructure.Repository.InvitoRepository;

public class UnitOfWork implements IUnitOfWork{

    private IHackathonRepository hackathonRepository;
    private ITeamRepository teamRepository;
    private IInvitoRepository invitoRepository;
    private ISegnalazioneRepository segnalazioneRepository;

    public UnitOfWork(IHackathonRepository hackathonRepository,
                      ITeamRepository teamRepository,
                      IInvitoRepository invitoRepository,
                      ISegnalazioneRepository segnalazioneRepository) {
        this.hackathonRepository = hackathonRepository;
        this.teamRepository = teamRepository;
        this.invitoRepository = invitoRepository;
        this.segnalazioneRepository = segnalazioneRepository;
    }

    public IHackathonRepository hackathonRepository() {
        return hackathonRepository;
    }
    public ITeamRepository teamRepository() {
        return teamRepository;
    }
    public IInvitoRepository invitoRepository() {return  invitoRepository;}
    public ISegnalazioneRepository segnalazioneRepository() { return segnalazioneRepository;}

    @Override
    public void saveChanges() {

    }
}
