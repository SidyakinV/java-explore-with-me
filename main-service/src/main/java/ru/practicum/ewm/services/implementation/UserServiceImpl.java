package ru.practicum.ewm.services.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.user.NewUserRequest;
import ru.practicum.ewm.exceptions.ConflictException;
import ru.practicum.ewm.exceptions.NotFoundException;
import ru.practicum.ewm.dto.user.UserDto;
import ru.practicum.ewm.mappers.UserMapper;
import ru.practicum.ewm.models.User;
import ru.practicum.ewm.repositories.UserRepository;
import ru.practicum.ewm.services.UserService;
import ru.practicum.ewm.utility.PageCalc;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto addUser(NewUserRequest dto) {
        if (userRepository.findByEmail(dto.getEmail()) != null) {
            throw new ConflictException("Пользователь с указанным email уже существует");
        }
        User user = UserMapper.mapDtoToUser(dto);
        User savedUser = userRepository.save(user);
        log.info("Добавлен новый пользователь: {}", savedUser);
        return UserMapper.mapUserToDto(savedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(String.format("User with id=%d was not found", userId));
        }
        userRepository.deleteById(userId);
        log.info("Пользователь с id={} удален", userId);
    }

    @Override
    public List<UserDto> getUsers(List<Long> ids, Integer from, Integer size) {
        List<User> users;
        if (ids == null || ids.isEmpty()) {
            users = userRepository.findAll(PageCalc.getPageable(from, size)).getContent();
        } else {
            users = userRepository.findAllById(ids);
        }
        log.info("Получен список пользователей");

        return users.stream()
                .map(UserMapper::mapUserToDto)
                .collect(Collectors.toList());
    }
}
