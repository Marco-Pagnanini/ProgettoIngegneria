package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.IRispostaRepository;
import org.example.Core.models.Risposta;

import java.util.ArrayList;
import java.util.List;

public class RispostaRepository implements IRispostaRepository {

    private List<Risposta> risposte;

    public RispostaRepository() {
        this.risposte = new ArrayList<>();
    }


    @Override
    public Risposta create(Risposta risposta) {
        risposta.setId(1L);
        risposte.add(risposta);
        return risposta;
    }

    @Override
    public Risposta delete(Long id) {
        for (Risposta risposta : risposte) {
            if (risposta.getId().equals(id)) {
                risposte.remove(risposta);
                return risposta;
            }
        }
        return null;
    }

    @Override
    public Risposta update(Risposta risposta) {
        for (Risposta risposta1 : risposte) {
            if (risposta.getId().equals(risposta.getId())) {
                risposta.setId(risposta1.getId());
                risposte.remove(risposta1);
                risposte.add(risposta);
            }
        }
        return risposta;
    }

    @Override
    public Risposta getById(Long id) {
        for (Risposta risposta : risposte) {
            if (risposta.getId().equals(id)) {
                return risposta;
            }
        }
        return null;
    }

    @Override
    public List<Risposta> getAll() {
        return risposte;
    }
}
