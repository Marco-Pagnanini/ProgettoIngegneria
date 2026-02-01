package org.example.Infrastructure.Service.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * gestisce tutte le operazione da salvare nel database
 */
public interface CallEventRepository extends JpaRepository<CallEventCalendar,Long> {

    List<CallEventCalendar> findByHackathonId(Long hackathonId);

    List<CallEventCalendar> findByMentorIdAndDateTimeAfter(Long mentorId, LocalDateTime from);

    List<CallEventCalendar> findByTeamId(Long teamId);

    List<CallEventCalendar> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
