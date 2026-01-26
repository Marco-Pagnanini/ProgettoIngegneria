package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.IInvitoRepository;
import org.example.Core.enums.StatoInvito;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Invito;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InvitoRepository implements IInvitoRepository {
    private List<Invito> inviti;

    private long nextId = 1L;
    public InvitoRepository() {
        this.inviti = new ArrayList<>();
    }


    @Override
    public Invito create(Invito invito) {
        invito.setId(nextId++);
        invito.setStato(StatoInvito.PENDENTE);
        inviti.add(invito);
        return invito;
    }

    @Override
    public Invito delete(Long id) {
        for (int idx = 0; idx < inviti.size(); idx++) {
            Invito i = inviti.get(idx);
            if (i.getId().equals(id)) {
                return inviti.remove(idx);
            }
        }
        return null;
    }

    @Override
    public Invito update(Invito invito) {
        for (int idx = 0; idx < inviti.size(); idx++) {
            Invito i = inviti.get(idx);
            if (i.getId().equals(invito.getId())) {
                inviti.set(idx, invito);
                return invito;
            }
        }
        return null;
    }


    @Override
    public Invito getById(Long id) {
        for(Invito i : inviti){
            if(i.getId().equals(id)){
                return i;
            }
        }
        return null;
    }

    @Override
    public List<Invito> getAll() {
       return inviti;
    }
}
