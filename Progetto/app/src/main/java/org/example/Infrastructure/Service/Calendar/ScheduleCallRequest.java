package org.example.Infrastructure.Service.Calendar;

import java.time.LocalDateTime;

/**
 * richiesta per creare la calendar call
 * @param title
 * @param dateTime
 * @param meetingLink
 */
public record ScheduleCallRequest(
        String title,
        LocalDateTime dateTime,
        String meetingLink
) {}