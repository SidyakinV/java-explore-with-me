package ru.practicum.ewm.services;

import org.springframework.data.domain.Pageable;
import ru.practicum.ewm.dto.category.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto dto);

    CategoryDto updateCategory(CategoryDto dto);

    void deleteCategory(Long catId);

    List<CategoryDto> getAllCategories(Pageable pageable);

    CategoryDto getCategoryById(Long catId);

}
