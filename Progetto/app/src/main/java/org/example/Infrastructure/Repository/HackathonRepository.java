package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.IHackathonRepository;
import org.example.Core.models.Hackathon;

import java.util.ArrayList;
import java.util.List;

public class HackathonRepository implements IHackathonRepository {
    private List<Hackathon> hackathons;

    public HackathonRepository() {
        hackathons = new ArrayList<>();
    }

    @Override
    public Hackathon create(Hackathon hackathon) {
        hackathon.setId(1L);
        hackathons.add(hackathon);
        return hackathon;
    }

    @Override
    public Hackathon update(Hackathon hackathon) {
        for(Hackathon h : hackathons){
            if(hackathon.getId().equals(h.getId())){
                hackathons.remove(h);
                hackathons.add(hackathon);
                return h;
            }
        }
        return null;
    }



    @Override
    public Hackathon delete(Long id) {
        for(Hackathon h : hackathons){
            if(h.getId().equals(id)){
                hackathons.remove(h);
                return h;
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
