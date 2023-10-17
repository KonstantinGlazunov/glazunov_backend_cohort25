package de.ait.task_05.services;

import de.ait.task_05.dtos.NewUsertDto;
import de.ait.task_05.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto register(NewUsertDto newUser);


    Object getUserById(Long currentUserId);

    List<UserDto> getAllUsers();

}
