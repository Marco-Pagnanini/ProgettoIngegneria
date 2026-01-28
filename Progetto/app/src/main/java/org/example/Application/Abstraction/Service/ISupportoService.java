package org.example.Application.Abstraction.Service;

import org.example.Api.Models.Request.SupportoRequest;
import org.example.Api.Models.Response.SupportoResponse;
import org.example.Core.models.Supporto;
import org.example.Infrastructure.Service.Calendar.CallEventCalendar;
import org.example.Infrastructure.Service.Calendar.ScheduleCallRequest;

import java.util.List;

public interface ISupportoService {
    public List<SupportoResponse> visualizzaSupporto(Long idHackathon);
    public SupportoResponse richiediSupporto(SupportoRequest request);
    public CallEventCalendar richiediCallEvent(Long idSupporto,Long idMentore, ScheduleCallRequest request);
}
