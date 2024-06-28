package ru.practicum.ewm.services;

import ru.practicum.ewm.dto.category.CategoryDto;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto dto);

    CategoryDto updateCategory(CategoryDto dto);

    void deleteCategory(Long catId);

}
