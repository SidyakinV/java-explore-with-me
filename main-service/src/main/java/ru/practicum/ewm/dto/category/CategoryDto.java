package ru.practicum.ewm.dto.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CategoryDto {

    private Long id;

    @Size(min = 1, max = 50)
    @NotBlank
    private String name;

}
