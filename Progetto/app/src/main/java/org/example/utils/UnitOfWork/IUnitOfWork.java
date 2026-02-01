package org.example.utils.UnitOfWork;

import org.example.Application.Abstraction.Repository.*;

/**
 * Design pattern Architetturale : l'UnitOfWork è un pattern che permette la consistenza dei dati
 * infatti tutte le repository vengono gestite da questa classe che fa tutte le operazione di business logic
 * poi le registra nel database
 * questo perchè se una business logic non va a buon fine per qualche motivo, tutte le operazione di scrittura o lettura nel db vengono eseguite
 * portando ad una inconsistenza di dati
 * usando invece alla fine il metodo saveChanges alla fine tutte le operazioni verranno salvate alla fine
 */
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
