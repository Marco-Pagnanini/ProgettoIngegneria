package org.example.utils.UnitOfWork;

import org.example.Application.Abstraction.Repository.IHackathonRepository;

public class UnitOfWork implements IUnitOfWork{

    private IHackathonRepository hackathonRepository;

    public UnitOfWork(IHackathonRepository hackathonRepository) {
        this.hackathonRepository = hackathonRepository;
    }

    public IHackathonRepository hackathonRepository() {
        return hackathonRepository;
    }

    @Override
    public void saveChanges() {

    }
}
