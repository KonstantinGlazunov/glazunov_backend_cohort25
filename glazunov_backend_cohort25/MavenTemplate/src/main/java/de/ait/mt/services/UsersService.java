package de.ait.mt.services;

import de.ait.mt.dto.NewUserDto;
import de.ait.mt.dto.UserDto;
import de.ait.mt.exceptions.RestException;
import de.ait.mt.models.User;
import de.ait.mt.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;


    public UserDto register(NewUserDto newUser) {

        if (usersRepository.existsByEmail(newUser.getEmail())) {
            throw new RestException(HttpStatus.CONFLICT,
                    "User with email <" + newUser.getEmail() + "> already exists");
        }

        User user = User.builder()
                .email(newUser.getEmail())
                .hashPassword(passwordEncoder.encode(newUser.getPassword()))
                .role(User.Role.USER)
                .build();

        usersRepository.save(user);

        return UserDto.from(user);
    }


    public UserDto getUserById(Long currentUserId) {
        return UserDto.from(usersRepository.findById(currentUserId).orElseThrow());
    }

  /*  @Override
    public UsersPage getAllUsers(int page, int size, String orderBy, Boolean desc) {

        Sort sort = Sort.by(orderBy);

        if (desc != null && desc) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        PageRequest pageRequest = PageRequest.of(page, size, sort); // запрос на получение данных с пагинацией и сортировкой

        Page<User> pageResult = usersRepository.findAll(pageRequest);

        return UsersPage
                .builder()
                .users(from(pageResult.getContent()))
                .totalPages(pageResult.getTotalPages())
                .build();
    }
*/

}
