package ru.practicum.ewm.services.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.dto.category.CategoryMapper;
import ru.practicum.ewm.models.Category;
import ru.practicum.ewm.repositories.CategoryRepository;
import ru.practicum.ewm.exceptions.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(CategoryDto dto) {
        Category category = CategoryMapper.mapDtoToCategory(dto);
        Category savedCategory = categoryRepository.save(category);
        log.info("Добавлена новая категория: {}", savedCategory);
        return CategoryMapper.mapCategoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto dto) {
        if (!categoryRepository.existsById(dto.getId())) {
            throw new NotFoundException(String.format("Category with id=%d was not found", dto.getId()));
        }
        Category category = CategoryMapper.mapDtoToCategory(dto);
        Category savedCategory = categoryRepository.save(category);
        log.info("Категория изменена: {}", savedCategory);
        return CategoryMapper.mapCategoryToDto(savedCategory);
    }

    @Override
    public void deleteCategory(Long catId) {
        if (!categoryRepository.existsById(catId)) {
            throw new NotFoundException(String.format("Category with id=%d was not found", catId));
        }
        categoryRepository.deleteById(catId);
        log.info("Категория с id={} удалена", catId);
    }

    @Override
    public List<CategoryDto> getAllCategories(Pageable pageable) {
        return null;
    }

    @Override
    public CategoryDto getCategoryById(Long catId) {
        return null;
    }

}
