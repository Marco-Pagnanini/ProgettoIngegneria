package org.example.utils.UnitOfWork;

import org.example.Application.Abstraction.Repository.IHackathonRepository;
import org.example.Application.Abstraction.Repository.IInvitoRepository;
import org.example.Application.Abstraction.Repository.ITeamRepository;

public interface IUnitOfWork {

    IHackathonRepository hackathonRepository();
    ITeamRepository teamRepository();
    IInvitoRepository invitoRepository();
    ISegnalazioneRepository segnalazioneRepository();
    void saveChanges();
}
