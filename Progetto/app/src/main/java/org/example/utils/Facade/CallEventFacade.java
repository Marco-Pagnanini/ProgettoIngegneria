package org.example.utils.Facade;

import lombok.RequiredArgsConstructor;
import org.example.Api.Exception.ResourceNotFoundException;
import org.example.Infrastructure.Service.Calendar.CallEventCalendar;
import org.example.Infrastructure.Service.Calendar.CallEventRepository;
import org.example.Infrastructure.Service.Calendar.ScheduleCallRequest;
import org.example.utils.UnitOfWork.IUnitOfWork;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CallEventFacade {

    private final CallEventRepository repository;
    private final IUnitOfWork unitOfWork;

    public CallEventFacade(CallEventRepository repository, IUnitOfWork unitOfWork) {
        this.repository = repository;
        this.unitOfWork = unitOfWork;
    }

    public CallEventCalendar schedule(ScheduleCallRequest request, Long idMentore, Long idTeam, Long idHackathon) {
        CallEventCalendar call = new CallEventCalendar();
        call.setTitle(request.title());
        call.setDateTime(request.dateTime());
        call.setMeetingLink(request.meetingLink());

        call.setMentor(unitOfWork.userStaffRepository().getById(idMentore));
        call.setTeam(unitOfWork.teamRepository().getById(idTeam));
        call.setHackathon(unitOfWork.hackathonRepository().getById(idHackathon));

        return repository.save(call);
    }

    public CallEventCalendar reschedule(Long callId, LocalDateTime newDateTime) {
        CallEventCalendar call = findById(callId);
        call.setDateTime(newDateTime);
        return repository.save(call);
    }

    public void cancel(Long callId) {
        CallEventCalendar call = findById(callId);
        repository.delete(call);
    }


    public List<CallEventCalendar> getUpcomingForMentor(Long mentorId) {
        return repository.findByMentorIdAndDateTimeAfter(mentorId, LocalDateTime.now());
    }

    public List<CallEventCalendar> getByTeam(Long teamId) {
        return repository.findByTeamId(teamId);
    }

    public List<CallEventCalendar> getByHackathon(Long hackathonId) {
        return repository.findByHackathonId(hackathonId);
    }

    public List<CallEventCalendar> getInRange(LocalDateTime from, LocalDateTime to) {
        return repository.findByDateTimeBetween(from, to);
    }

    private CallEventCalendar findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CallEvent not found: " + id));
    }
}