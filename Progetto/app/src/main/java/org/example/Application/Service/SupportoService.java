package org.example.Application.Service;

import org.example.Api.Models.Mapper.SupportoMapper;
import org.example.Api.Models.Request.SupportoRequest;
import org.example.Application.Abstraction.Service.ISupportoService;
import org.example.Core.enums.SupportoState;
import org.example.Core.models.Supporto;
import org.example.Infrastructure.Service.Calendar.CallEventCalendar;
import org.example.Infrastructure.Service.Calendar.ScheduleCallRequest;
import org.example.utils.Facade.CallEventFacade;
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SupportoService implements ISupportoService {
    private final IUnitOfWork unitOfWork;
    private CallEventFacade callEventFacade;

    public SupportoService(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public List<Supporto> visualizzaSupporto(Long idMentore){
        List<Supporto> all = unitOfWork.supportoRepository().getAll();
        List<Supporto> response = new ArrayList<>();
        for(Supporto supporto : response){
            if(Objects.equals(supporto.getUserStaff().getId(), idMentore)){
                response.add(supporto);
            }
        }
        unitOfWork.saveChanges();
        return response;
    }


    @Override
    public Supporto richiediSupporto(SupportoRequest request) {
        Supporto supporto = SupportoMapper.toEntity(request, unitOfWork);

        unitOfWork.supportoRepository().create(supporto);
        unitOfWork.saveChanges();

        return supporto;
    }

    @Override
    public CallEventCalendar richiediCallEvent(Long idSupporto, ScheduleCallRequest request) {
        Supporto supporto = unitOfWork.supportoRepository().getById(idSupporto);
        supporto.setState(SupportoState.PRESA_IN_CARICA);
        unitOfWork.supportoRepository().update(supporto);
        unitOfWork.saveChanges();
        return callEventFacade.schedule(request);
    }
}
