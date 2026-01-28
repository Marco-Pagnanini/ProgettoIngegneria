package org.example.Application.Service;

import org.example.Api.Models.Mapper.SupportoMapper;
import org.example.Api.Models.Request.SupportoRequest;
import org.example.Api.Models.Response.SupportoResponse;
import org.example.Application.Abstraction.Service.ISupportoService;
import org.example.Core.enums.SupportoState;
import org.example.Core.models.Supporto;
import org.example.Core.models.UserStaff;
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

    public SupportoService(IUnitOfWork unitOfWork, CallEventFacade callEventFacade) {
        this.unitOfWork = unitOfWork;
        this.callEventFacade = callEventFacade;
    }

    public List<SupportoResponse> visualizzaSupporto(Long idHackathon){
        List<Supporto> all = unitOfWork.supportoRepository().getAll();
        List<SupportoResponse> response = new ArrayList<>();
        for(Supporto supporto : all){
            if(Objects.equals(supporto.getHackathon().getId(), idHackathon)){
                response.add(SupportoMapper.toResponse(supporto));
            }
        }
        unitOfWork.saveChanges();
        return response;
    }


    @Override
    public SupportoResponse richiediSupporto(SupportoRequest request) {
        Supporto supporto = SupportoMapper.toEntity(request, unitOfWork);


        unitOfWork.supportoRepository().create(supporto);
        unitOfWork.saveChanges();

        return SupportoMapper.toResponse(supporto);
    }


    @Override
    public CallEventCalendar richiediCallEvent(Long idSupporto,Long idMentore, ScheduleCallRequest request) {
        Supporto supporto = unitOfWork.supportoRepository().getById(idSupporto);
        UserStaff mentore = unitOfWork.userStaffRepository().getById(idMentore);
        supporto.setState(SupportoState.PRESA_IN_CARICA);
        supporto.setUserStaff(mentore);

        unitOfWork.supportoRepository().update(supporto);
        unitOfWork.saveChanges();
        return callEventFacade.schedule(request, idMentore, supporto.getHackathon().getId(), supporto.getTeam().getId());
    }
}
