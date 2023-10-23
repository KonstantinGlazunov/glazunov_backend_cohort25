package de.ait.mt.controllers;

import de.ait.mt.controllers.api.UsersApi;
import de.ait.mt.dto.NewUserDto;
import de.ait.mt.dto.UserDto;
import de.ait.mt.security.details.AuthenticatedUser;
import de.ait.mt.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class UsersController implements UsersApi {

    private final UsersService usersService;

 /*   @Override
    public UsersPage getAllUsers(int page, int size, String orderBy, Boolean desc) {
        return usersService.getAllUsers(page, size, orderBy, desc);
    }
*/
    @Override
    public UserDto register(NewUserDto newUser) {
        return usersService.register(newUser);
    }

    @Override
    public UserDto getProfile(AuthenticatedUser user) {
        Long currentUserId = user.getId();
        return usersService.getUserById(currentUserId);
    }


}
