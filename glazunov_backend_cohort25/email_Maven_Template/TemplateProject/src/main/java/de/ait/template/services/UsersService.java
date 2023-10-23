package de.ait.template.services;

import de.ait.template.dto.NewUserDto;
import de.ait.template.dto.UserDto;
import de.ait.template.exceptions.RestException;
import de.ait.template.mail.TemlateProjectMailSender;
import de.ait.template.models.ConfirmationCode;
import de.ait.template.models.User;
import de.ait.template.repositories.ConfirmationCodsRepository;
import de.ait.template.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static de.ait.template.dto.UserDto.from;


@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    private final ConfirmationCodsRepository confirmationCodsRepository;
    private final TemlateProjectMailSender mailSender;


    @Transactional
    public UserDto register(NewUserDto newUser) {

        if (usersRepository.existsByEmail(newUser.getEmail())) {
            throw new RestException(HttpStatus.CONFLICT,
                    "User with email <" + newUser.getEmail() + "> already exists");
        }

        User user = User.builder()
                .email(newUser.getEmail())
                .hashPassword(passwordEncoder.encode(newUser.getPassword()))
                .role(User.Role.USER)
                .state(User.State.NOT_CONFIRMED)
                .build();

        usersRepository.save(user);

        String codeValue = UUID.randomUUID().toString();

        ConfirmationCode code = ConfirmationCode.builder()
                .code(codeValue)
                .user(user)
                .expiredDateTime(LocalDateTime.now().plusMinutes(1))
                .build();
        confirmationCodsRepository.save(code);

        mailSender.sendMail(user.getEmail(), "Registration", codeValue);

        return from(user);
    }

    public UserDto getUserById(Long currentUserId) {
        return from(usersRepository.findById(currentUserId).orElseThrow());
    }
}
