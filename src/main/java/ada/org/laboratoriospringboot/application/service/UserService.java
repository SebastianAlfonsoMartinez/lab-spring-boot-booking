package ada.org.laboratoriospringboot.application.service;
import ada.org.laboratoriospringboot.domain.dto.UserDto;
import ada.org.laboratoriospringboot.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    private final Map<Integer, User> userMap = new HashMap<>();
    private final AtomicInteger sequence = new AtomicInteger();

    @Override
    public void createUser(UserDto userDto) {
        int userId = sequence.incrementAndGet();
        User user = new User(userId, userDto.name(), userDto.email());
        userMap.put(userId, user);
    }

    @Override
    public Optional<UserDto> getUser(Integer id) {
        User user = userMap.get(id);
        if (user != null) {
            return Optional.of(new UserDto(user.getId(), user.getName(), user.getEmail()));
        }
        return Optional.empty();
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userMap.values().stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Integer id, UserDto userDto) {
        User user = new User(id, userDto.name(), userDto.email());
        userMap.put(id, user);
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public void deleteUser(Integer id) {
        userMap.remove(id);
    }
}

