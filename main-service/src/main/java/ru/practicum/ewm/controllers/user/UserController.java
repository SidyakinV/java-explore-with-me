package ru.practicum.ewm.controllers.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.services.user.UserService;
import ru.practicum.ewm.dto.user.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto addUser(
            @RequestBody @Valid UserDto dto
    ) {
        log.info("POST-request '/admin/users' with parameters: dto {}", dto);
        return userService.addUser(dto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PathVariable Long userId
    ) {
        log.info("DELETE-request '/admin/users/{}'", userId);
        userService.deleteUser(userId);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDto> getUsers(
            @RequestParam(required = false) List<Long> ids,
            @RequestParam(defaultValue = "0") @Valid @Min(value = 0) Integer from,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        log.info("GET-request '/admin/users' with parameters: ids {}, from {}, size {}", ids, from, size);
        return userService.getUsers(ids, from, size);
    }

}
