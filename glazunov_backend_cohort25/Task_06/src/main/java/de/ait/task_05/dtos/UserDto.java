package de.ait.task_05.dtos;

import de.ait.task_05.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Participant", description = "Participants data")
public class UserDto {
    @Schema(description = "participant id", example = "1")
    private Long id;
    @Schema(description = "participant name", example = "Jon")
    private String name;
    @Schema(description = "ParticipantEmail", example = "user@mail.com")
    private String email;

    @Schema(description = "Роль пользователя", example = "USER")
    private String role;

public static UserDto from (User user){
    return UserDto.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .role(user.getRole().toString())
            .build();
}
public static List<UserDto> from (Collection<User> users){
    return users.stream()
            .map(UserDto::from)
            .collect(Collectors.toList());
}
}
