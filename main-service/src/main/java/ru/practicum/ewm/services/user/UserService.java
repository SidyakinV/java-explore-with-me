package ru.practicum.ewm.services.user;

import ru.practicum.ewm.dto.user.UserDto;

import java.util.List;

public interface UserService {

    UserDto addUser(UserDto dto);

    void deleteUser(Long userId);

    List<UserDto> getUsers(List<Long> ids, Integer from, Integer size);

}
