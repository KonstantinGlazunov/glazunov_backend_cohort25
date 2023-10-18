package de.ait.task_05.controllers;

import de.ait.task_05.controllers.api.UsersApi;
import de.ait.task_05.dtos.NewUsertDto;
import de.ait.task_05.dtos.UserDto;
import de.ait.task_05.security.details.AuthenticatedUser;
import de.ait.task_05.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController

public class UsersController implements UsersApi {

    private final UserService usersService;


    @Override
    public List<UserDto> getAllUsers() {
        return usersService.getAllUsers();
    }

    @Override
    public UserDto register( NewUsertDto newParticipant) {
        return usersService.register(newParticipant);
    }

    @Override

    public UserDto getProfile(AuthenticatedUser  user) {
        Long currentUserId = user.getId();
        return usersService.getUserById(currentUserId);
    }


}
