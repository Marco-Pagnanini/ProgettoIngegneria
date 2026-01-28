package org.example.Infrastructure.Service.Calendar;

import java.time.LocalDateTime;

public record ScheduleCallRequest(
        String title,
        LocalDateTime dateTime,
        String meetingLink
) {}