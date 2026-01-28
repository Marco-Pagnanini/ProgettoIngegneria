package org.example.utils.UnitOfWork;

import org.example.Application.Abstraction.Repository.*;

public interface IUnitOfWork {

    IHackathonRepository hackathonRepository();
    ITeamRepository teamRepository();
    IInvitoRepository invitoRepository();
    ISegnalazioneRepository segnalazioneRepository();
    IUserRepository userRepository();
    ISottoMissioneRepository sottoMissioneRepository();
    IRispostaRepository rispostaRepository();
    IValutazioneRepository valutazioneRepository();
    IUserStaffRepository userStaffRepository();
    ISupportoRepository supportoRepository();
    void saveChanges();

}
