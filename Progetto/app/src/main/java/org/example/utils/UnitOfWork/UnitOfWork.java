package org.example.utils.UnitOfWork;

import org.example.Application.Abstraction.Repository.IHackathonRepository;
import org.example.Application.Abstraction.Repository.IInvitoRepository;
import org.example.Application.Abstraction.Repository.ITeamRepository;
import org.example.Core.models.Invito;
import org.example.Infrastructure.Repository.InvitoRepository;

public class UnitOfWork implements IUnitOfWork{

    private IHackathonRepository hackathonRepository;
    private ITeamRepository teamRepository;
    private IInvitoRepository invitoRepository;

    public UnitOfWork(IHackathonRepository hackathonRepository, ITeamRepository teamRepository, IInvitoRepository invitoRepository) {
        this.hackathonRepository = hackathonRepository;
        this.teamRepository = teamRepository;
        this.invitoRepository = invitoRepository;
    }

    public IHackathonRepository hackathonRepository() {
        return hackathonRepository;
    }
    public ITeamRepository teamRepository() {
        return teamRepository;
    }
    public IInvitoRepository invitoRepository() {return  invitoRepository;}

    @Override
    public void saveChanges() {

    }
}
