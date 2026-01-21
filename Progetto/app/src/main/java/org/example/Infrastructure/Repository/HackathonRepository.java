package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.IHackathonRepository;
import org.example.Core.models.Hackathon;

import java.util.ArrayList;
import java.util.List;

public class HackathonRepository implements IHackathonRepository {
    private List<Hackathon> hackathons;
    private long nextId = 1L;

    public HackathonRepository() {
        hackathons = new ArrayList<>();
    }

    @Override
    public Hackathon create(Hackathon hackathon) {
        //Creazione degli Id
        hackathon.setId(nextId++);
        hackathons.add(hackathon);
        return hackathon;
    }

    @Override
    public Hackathon update(Hackathon hackathon) {
        for (int i = 0; i < hackathons.size(); i++) {
            Hackathon h = hackathons.get(i);
            if (hackathon.getId().equals(h.getId())) {
                hackathons.set(i, hackathon);
                return h; // oppure return hackathon, dipende da cosa vuoi
            }
        }
        return null;
    }

    @Override
    public Hackathon delete(Long id) {
        for (int i = 0; i < hackathons.size(); i++) {
            if (hackathons.get(i).getId().equals(id)) {
                return hackathons.remove(i);
            }
        }
        return null;
    }



    @Override
    public Hackathon getById(Long id) {
        for(Hackathon h : hackathons){
            if(h.getId().equals(id)){
                return h;
            }
        }
        return null;
    }

    @Override
    public List<Hackathon> getAll() {
        return hackathons;
    }
}
