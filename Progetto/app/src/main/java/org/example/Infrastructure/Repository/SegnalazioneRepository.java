package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.ISegnalazioneRepository;
import org.example.Core.enums.StatoSegnalazione;
import org.example.Core.models.Invito;
import org.example.Core.models.Segnalazione;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SegnalazioneRepository implements ISegnalazioneRepository {
    private List<Segnalazione> segnalazioni;

    private long nextId = 1L;

    public SegnalazioneRepository() {
        this.segnalazioni = new ArrayList<>();
    }

        public Segnalazione create (Segnalazione segnalazione){
            segnalazione.setId(nextId++);
            segnalazione.setStatoSegnalazione(StatoSegnalazione.APERTA);
            this.segnalazioni.add(segnalazione);
            return segnalazione;
        }

    @Override
    public Segnalazione delete(Long id) {
        for (int idx = 0; idx < segnalazioni.size(); idx++) {
            Segnalazione s = segnalazioni.get(idx);
            if (s.getId().equals(id)) {
                return segnalazioni.remove(idx);
            }
        }
        return null;
    }

    @Override
    public Segnalazione update(Segnalazione segnalazione) {
        for (int idx = 0; idx < segnalazioni.size(); idx++) {
            Segnalazione s = segnalazioni.get(idx);
            if (s.getId().equals(segnalazione.getId())) {
                segnalazioni.set(idx, segnalazione);
                return segnalazione;
            }
        }
        return null;
    }


    @Override
        public Segnalazione getById (Long id){
            for (Segnalazione i : segnalazioni) {
                if (i.getId().equals(id)) {
                    return i;
                }
            }
            return null;
        }

        @Override
        public List<Segnalazione> getAll () {
            return segnalazioni;
        }
    }
