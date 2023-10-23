package de.ait.mt.dto;


import de.ait.mt.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "User", description = "Users data")
public class UserDto {

    @Schema(description = "User id", example = "1")
    private Long id;

    @Schema(description = "User first name", example = "Marsel")
    private String firstName;

    @Schema(description = "User Last Name", example = "Sidikov")
    private String lastName;

    @Schema(description = "User`s Email ", example = "user@mail.com")
    private String email;

    @Schema(description = "User`s Role", example = "USER")
    private String role;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }

    public static List<UserDto> from(Collection<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
