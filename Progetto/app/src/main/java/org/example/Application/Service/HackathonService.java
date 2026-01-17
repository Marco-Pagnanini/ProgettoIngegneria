package org.example.Application.Service;

import org.example.Application.Abstraction.Service.IHackathonService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.models.Hackathon;
import org.example.utils.UnitOfWork.UnitOfWork;

import java.util.List;

public class HackathonService implements IHackathonService {

    private final UnitOfWork unitOfWork;
    private final Validator<Hackathon> hackathonValidator;


    public HackathonService(UnitOfWork unitOfWork, Validator<Hackathon> hackathonValidator) {
        this.unitOfWork = unitOfWork;
        this.hackathonValidator = hackathonValidator;
    }

    @Override
    public Hackathon addHackathon(Hackathon hackathon) {

        if(!hackathonValidator.validate(hackathon)) return null;

        unitOfWork.hackathonRepository().create(hackathon);
        unitOfWork.saveChanges();

        return hackathon;
    }

    @Override
    public Hackathon updateHackathon(Hackathon hackathon) {
        if(!hackathonValidator.validate(hackathon)) return null;
        Hackathon response = unitOfWork.hackathonRepository().update(hackathon);
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Hackathon deleteHackathon(Long id) {
        Hackathon response = unitOfWork.hackathonRepository().delete(id);
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Hackathon getHackathonById(Long id) {
        Hackathon response = unitOfWork.hackathonRepository().getById(id);
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public List<Hackathon> getAllHackathon() {
        List<Hackathon> response = unitOfWork.hackathonRepository().getAll();
        unitOfWork.saveChanges();
        return response;
    }
}
