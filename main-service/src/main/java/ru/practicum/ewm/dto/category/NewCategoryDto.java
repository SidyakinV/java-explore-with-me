package ru.practicum.ewm.dto.category;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class NewCategoryDto {

    @Size(min = 1, max = 50)
    private String name;

}
