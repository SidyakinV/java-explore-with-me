package ru.practicum.ewm.mappers;

import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.models.Category;

public class CategoryMapper {

    public static Category mapDtoToCategory(CategoryDto dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }

    public static CategoryDto mapCategoryToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

}
