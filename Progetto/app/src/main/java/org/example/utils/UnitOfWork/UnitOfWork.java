package org.example.utils.UnitOfWork;

import org.example.Application.Abstraction.Repository.IHackathonRepository;
import org.example.Application.Abstraction.Repository.ITeamRepository;

public class UnitOfWork implements IUnitOfWork{

    private IHackathonRepository hackathonRepository;
    private ITeamRepository teamRepository;

    public UnitOfWork(IHackathonRepository hackathonRepository, ITeamRepository teamRepository) {
        this.hackathonRepository = hackathonRepository;
        this.teamRepository = teamRepository;
    }

    public IHackathonRepository hackathonRepository() {
        return hackathonRepository;
    }
    public ITeamRepository teamRepository() {
        return teamRepository;
    }

    @Override
    public void saveChanges() {

    }
}
