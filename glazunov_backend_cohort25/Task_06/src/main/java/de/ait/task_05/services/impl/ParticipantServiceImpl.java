package de.ait.task_05.services.impl;

import de.ait.task_05.dtos.NewUsertDto;
import de.ait.task_05.dtos.UserDto;
import de.ait.task_05.exeptions.RestExeption;
import de.ait.task_05.models.User;
import de.ait.task_05.repositories.UsersRepository;
import de.ait.task_05.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ParticipantServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    @Override
    public UserDto register(NewUsertDto newUser) {
        if (usersRepository.existsByEmail(newUser.getEmail())){
            throw new RestExeption(HttpStatus.CONFLICT,
                    "Participant with email <" + newUser.getEmail() + "> already exist");
        }
        User user = User.builder()
                .name(newUser.getName())
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .role(User.Role.USER)
                .build();
        usersRepository.save(user);
        return UserDto.from(user);
    }

    @Override
    public UserDto getUserById(Long currentUserId) {
        return UserDto.from(usersRepository.findById(currentUserId).orElseThrow());
    }

    @Override
    public List<UserDto> getAllUsers() {
        return  UserDto.from(usersRepository.findAll());
    }


}
