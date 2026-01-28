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
@RequiredArgsConstructor
public class CallEventFacade {

    private final CallEventRepository repository;
    private final IUnitOfWork unitOfWork;

    public CallEventCalendar schedule(ScheduleCallRequest request) {
        CallEventCalendar call = new CallEventCalendar();
        call.setTitle(request.title());
        call.setDateTime(request.dateTime());
        call.setMeetingLink(request.meetingLink());

        call.setMentor(unitOfWork.userStaffRepository().getById(request.mentorId()));
        call.setTeam(unitOfWork.teamRepository().getById(request.teamId()));
        call.setHackathon(unitOfWork.hackathonRepository().getById(request.hackathonId()));

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