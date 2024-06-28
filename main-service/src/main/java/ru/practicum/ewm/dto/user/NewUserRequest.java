package ru.practicum.ewm.dto.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class NewUserRequest {

    @Size(min = 6, max = 254)
    @Email
    private String email;

    @Size(min = 2, max = 250)
    private String name;

}
