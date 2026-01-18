package org.example.utils.UnitOfWork;

import org.example.Application.Abstraction.Repository.IHackathonRepository;
import org.example.Application.Abstraction.Repository.IInvitoRepository;
import org.example.Application.Abstraction.Repository.ITeamRepository;
import org.example.Core.models.Invito;
import org.example.Infrastructure.Repository.InvitoRepository;

public class UnitOfWork implements IUnitOfWork{

    private IHackathonRepository hackathonRepository;
    private ITeamRepository teamRepository;
    private InvitoRepository InvitoRepository;

    public UnitOfWork(IHackathonRepository hackathonRepository, ITeamRepository teamRepository, InvitoRepository invitoRepository) {
        this.hackathonRepository = hackathonRepository;
        this.teamRepository = teamRepository;
        this.InvitoRepository = invitoRepository;
    }

    public IHackathonRepository hackathonRepository() {
        return hackathonRepository;
    }
    public ITeamRepository teamRepository() {
        return teamRepository;
    }
    public IInvitoRepository invitoRepository() {return  invitoRepository();}

    @Override
    public void saveChanges() {

    }
}
