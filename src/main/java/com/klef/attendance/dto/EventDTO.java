package com.klef.attendance.dto;

import java.time.LocalDate;

import com.klef.attendance.entity.EventStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDTO {

    private String eventName;
    private String eventDescription;
    private LocalDate eventDate;
    private String eventVenue;
    private Integer silPointsAwarded;
    private EventStatus eventStatus;
    private Boolean active;
}