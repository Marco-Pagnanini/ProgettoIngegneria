package org.example.Application.Service;

import org.example.Api.Exception.BadRequestException;
import org.example.Api.Exception.ConflictException;
import org.example.Api.Exception.ResourceNotFoundException;
import org.example.Api.Exception.ValidationException;
import org.example.Api.Models.Request.HackathonRequest;
import org.example.Api.Models.Request.PaymentRequest;
import org.example.Application.Abstraction.Service.IHackathonService;
import org.example.Application.Abstraction.Validator.Validator;
import org.example.Core.enums.PaymentType;
import org.example.Core.enums.State;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Segnalazione;
import org.example.Core.models.Team;
import org.example.Core.models.UserStaff;
import org.example.utils.Builder.HackathonBuilderImplementation;
import org.example.utils.Strategy.PaymentStrategy;
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class HackathonService implements IHackathonService {

    private final IUnitOfWork unitOfWork;
    private final Validator<Hackathon> hackathonValidator;
    private final Map<PaymentType, PaymentStrategy> paymentStrategies;


    public HackathonService(IUnitOfWork unitOfWork, Validator<Hackathon> hackathonValidator, List<PaymentStrategy> strategies) {
        this.unitOfWork = unitOfWork;
        this.hackathonValidator = hackathonValidator;
        this.paymentStrategies = strategies.stream()
                .collect(Collectors.toMap(PaymentStrategy::getType, Function.identity()));
    }

    @Override
    public Hackathon creazioneHackathon(Long idOrganizzatore,HackathonRequest request) {
        UserStaff giudice = unitOfWork.userStaffRepository().getById(request.getIdGiudice());
        if(giudice == null) throw new ResourceNotFoundException("giudice con id " + request.getIdGiudice() + " non trovato");

        List<UserStaff> mentori = new ArrayList<>();
        for(Long idMentore : request.getIdMentori()) {
            UserStaff mentore = unitOfWork.userStaffRepository().getById(idMentore);
            if(mentore == null) throw new ResourceNotFoundException("mentore non trovato");
            mentori.add(mentore);
        }

        UserStaff organizzatore =  unitOfWork.userStaffRepository().getById(idOrganizzatore);


        Hackathon hackathon = new HackathonBuilderImplementation()
                .nome(request.getNome())
                .regolamento(request.getRegolamento())
                .argomento(request.getArgomento())
                .scadenzaIscrizione(request.getScadenzaIscrizioni())
                .dataInizio(request.getDataInizio())
                .dataFine(request.getDataFine())
                .luogo(request.getLuogo())
                .premio(request.getPremio())
                .dimensioneMassimaTeam(request.getDimensioneMassimaTeam())
                .dimensioneMinimaTeam(request.getDimensioneMinimaTeam())
                .numeroMassimoPersone(request.getNumeroMassimoPersone())
                .numeroMinimoPersone(request.getNumeroMinimoPersone())
                .giudice(giudice)
                .mentori(mentori)
                .organizzatore(organizzatore)
                .build();

        if(!hackathonValidator.validate(hackathon)){
            throw new ValidationException("Dati hackathon non validi");
        }

        unitOfWork.hackathonRepository().create(hackathon);
        unitOfWork.saveChanges();

        return hackathon;
    }

    @Override
    public Hackathon updateHackathon(Hackathon hackathon) {
        if(!hackathonValidator.validate(hackathon)) {
            throw new ValidationException("Dati hackathon non validi");
        }
        Hackathon response = unitOfWork.hackathonRepository().update(hackathon);
        if(response == null) {
            throw new ResourceNotFoundException("Hackathon non trovato");
        }
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Hackathon deleteHackathon(Long id) {
        Hackathon response = unitOfWork.hackathonRepository().delete(id);
        if(response == null) {
            throw new ResourceNotFoundException("Hackathon con id " + id + " non trovato");
        }
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Hackathon getHackathonById(Long id) {
        Hackathon response = unitOfWork.hackathonRepository().getById(id);
        if(response == null) {
            throw new ResourceNotFoundException("Hackathon con id " + id + " non trovato");
        }
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public List<Hackathon> visualizzaHackathon() {
        List<Hackathon> response = unitOfWork.hackathonRepository().getAll();
        unitOfWork.saveChanges();
        return response;
    }

    @Override
    public Hackathon iscrizioneTeam(Long idTeam, Long idHackathon) {
        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        if(hackathon == null) {
            throw new ResourceNotFoundException("Hackathon con id " + idHackathon + " non trovato");
        }

        Team toFind = unitOfWork.teamRepository().getById(idTeam);
        if(toFind == null) {
            throw new ResourceNotFoundException("Team con id " + idTeam + " non trovato");
        }

        if(hackathon.getStato() != State.IN_ISCRIZIONE) {
            throw new BadRequestException("L'hackathon non è in fase di iscrizione");
        }

        if(hackathon.getTeams().contains(toFind)) {
            throw new ConflictException("Il team è già iscritto a questo hackathon");
        }

        int teamSize = toFind.getMembriTeam().size() + 1;
        if(hackathon.getDimensioneMassimaTeam() < teamSize) {
            throw new BadRequestException("Il team supera la dimensione massima consentita");
        }
        if(hackathon.getDimensioneMinimaTeam() > teamSize) {
            throw new BadRequestException("Il team non raggiunge la dimensione minima richiesta");
        }

        List<Team> teams = hackathon.getTeams();
        if(hackathon.getNumeroMassimoPersone() < numPersone(teams) + teamSize) {
            throw new BadRequestException("Numero massimo di partecipanti raggiunto");
        }

        teams.add(toFind);
        hackathon.setTeams(teams);

        unitOfWork.hackathonRepository().update(hackathon);
        unitOfWork.saveChanges();

        return hackathon;
    }

    @Override
    public List<Hackathon> getAllHackathon() {
        List<Hackathon> response =  unitOfWork.hackathonRepository().getAll();
        unitOfWork.saveChanges();
        return  response;
    }

    @Override
    public Boolean assegnaVincitore(PaymentType type,Long idTeam, Long idHackathon) {
        PaymentStrategy strategy = paymentStrategies.get(type);
        Team vincitore = unitOfWork.teamRepository().getById(idTeam);
        if(vincitore == null) {
            throw new ResourceNotFoundException("Team con id " + idTeam + " non trovato");
        }

        Hackathon hackathon = unitOfWork.hackathonRepository().getById(idHackathon);
        if(hackathon == null) {
            throw new ResourceNotFoundException("Hackathon non trovato");
        }

        hackathon.setVincitore(vincitore);

        PaymentRequest request = new PaymentRequest();
        request.setAmount(hackathon.getPremio());
        request.setTeam(vincitore);
        if(strategy.pay(request)){
            return true;
        }
        else {
            throw new BadRequestException("Impossibile pagamento");
        }
    }

    private int numPersone(List<Team> teams){
        int count = 0;
        for(Team team : teams){
            count += team.getMembriTeam().size() + 1;
        }
        return count;
    }
}
