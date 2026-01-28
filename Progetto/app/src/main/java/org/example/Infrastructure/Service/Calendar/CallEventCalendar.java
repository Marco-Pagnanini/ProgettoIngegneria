package org.example.Infrastructure.Service.Calendar;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Core.models.Hackathon;
import org.example.Core.models.Team;
import org.example.Core.models.UserStaff;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CallEventCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDateTime dateTime;
    private String meetingLink;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserStaff mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hackathon hackathon;
}