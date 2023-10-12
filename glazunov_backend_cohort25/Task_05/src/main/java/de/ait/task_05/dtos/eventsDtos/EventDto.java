package de.ait.task_05.dtos.eventsDtos;

import de.ait.task_05.models.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EventDto {
    private Long id;
    private String title;

    private String date;

    private String siteId;


    public static EventDto from(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .date(event.getDate().toString())
                .siteId(event.getSite().getId().toString())
                .build();
    }
}

