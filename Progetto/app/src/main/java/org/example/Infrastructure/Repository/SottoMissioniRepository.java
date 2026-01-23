package org.example.Infrastructure.Repository;

import org.example.Application.Abstraction.Repository.ISottoMissioneRepository;
import org.example.Core.models.sottoMissioni.SottoMissione;

import java.util.ArrayList;
import java.util.List;

public class SottoMissioniRepository implements ISottoMissioneRepository {

    private List<SottoMissione> sottoMissioni;

    public SottoMissioniRepository() {
        sottoMissioni = new ArrayList<>();
    }

    @Override
    public SottoMissione create(SottoMissione sottoMissione) {
        sottoMissione.setId(1L);
        sottoMissioni.add(sottoMissione);
        return sottoMissione;
    }

    @Override
    public SottoMissione delete(Long id) {
        for (SottoMissione m : sottoMissioni) {
            if (m.getId().equals(id)) {
                sottoMissioni.remove(m);
                return m;
            }
        }
        return null;
    }

    @Override
    public SottoMissione update(SottoMissione sottoMissione) {
        for (SottoMissione m : sottoMissioni) {
            if (m.getId().equals(sottoMissione.getId())) {
                sottoMissioni.remove(m);
                sottoMissioni.add(sottoMissione);
                return m;
            }
        }
        return null;
    }

    @Override
    public SottoMissione getById(Long id) {
        for (SottoMissione m : sottoMissioni) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public List<SottoMissione> getAll() {
        return sottoMissioni;
    }
}
