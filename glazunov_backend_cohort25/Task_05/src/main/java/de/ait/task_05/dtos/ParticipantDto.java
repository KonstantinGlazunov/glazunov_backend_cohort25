package de.ait.task_05.dtos;

import de.ait.task_05.models.Participant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Participant", description = "Participants data")
public class ParticipantDto {
    @Schema(description = "participant id", example = "1")
    private Long id;
    @Schema(description = "participant name", example = "Jon")
    private String name;
    @Schema(description = "ParticipantEmail", example = "user@mail.com")
    private String email;

public static ParticipantDto from (Participant participant){
    return ParticipantDto.builder()
            .id(participant.getId())
            .name(participant.getName())
            .email(participant.getEmail())
            .build();
}
public static List<ParticipantDto> from (Set<Participant> participants){
    return participants.stream()
            .map(ParticipantDto::from)
            .collect(Collectors.toList());
}
}
