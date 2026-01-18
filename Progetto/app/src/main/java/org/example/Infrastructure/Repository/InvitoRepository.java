package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.IInvitoRepository;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Invito;

import java.util.ArrayList;
import java.util.List;

public class InvitoRepository implements IInvitoRepository {
    private List<Invito> inviti;

    public InvitoRepository() {
        this.inviti = new ArrayList<>();
    }


    @Override
    public Invito create(Invito invito) {
        invito.setId(1L);
        inviti.add(invito);
        return invito;
    }

    @Override
    public Invito delete(Long id) {
        for(Invito i : inviti){
            if(i.getId().equals(id)){
                inviti.remove(i);
                return i;
            }
        }
        return null;

    }

    @Override
    public Invito update(Invito invito) {
        for(Invito i : inviti){
            if(i.getId().equals(invito.getId())){
                inviti.remove(i);
                inviti.add(invito);
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
