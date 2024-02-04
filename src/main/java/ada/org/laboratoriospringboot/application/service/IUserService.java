package ada.org.laboratoriospringboot.application.service;

import ada.org.laboratoriospringboot.domain.dto.UserDto;
import ada.org.laboratoriospringboot.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    void createUser(UserDto userDto);
    Optional<UserDto> getUser(Integer id);
    List<UserDto> getAllUsers();
    UserDto updateUser(Integer id, UserDto userDto);
    void deleteUser(Integer id);
}

