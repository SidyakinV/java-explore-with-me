package ru.practicum.ewm.services.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.dto.category.CategoryMapper;
import ru.practicum.ewm.exceptions.ConflictException;
import ru.practicum.ewm.exceptions.ValidationException;
import ru.practicum.ewm.models.Category;
import ru.practicum.ewm.repositories.CategoryRepository;
import ru.practicum.ewm.exceptions.NotFoundException;
import ru.practicum.ewm.repositories.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;

    @Override
    public CategoryDto addCategory(CategoryDto dto) {
        checkUniqueCatName(dto.getName(), null);
        Category category = CategoryMapper.mapDtoToCategory(dto);
        Category savedCategory = categoryRepository.save(category);
        log.info("Добавлена новая категория: {}", savedCategory);
        return CategoryMapper.mapCategoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto dto) {
        if (dto.getId() == null) {
            throw new ValidationException("Не определен id категории");
        }
        if (!categoryRepository.existsById(dto.getId())) {
            throw new NotFoundException(String.format("Category with id=%d was not found", dto.getId()));
        }

        checkUniqueCatName(dto.getName(), dto.getId());

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
        if (eventRepository.existsByCatId(catId)) {
            throw new ConflictException("Нельзя удалить категорию, по которой существуют события");
        }

        categoryRepository.deleteById(catId);
        log.info("Категория с id={} удалена", catId);
    }

    @Override
    public List<CategoryDto> getAllCategories(Pageable pageable) {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper::mapCategoryToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long catId) {
        Category category = categoryRepository.findById(catId).orElseThrow(() ->
                new NotFoundException("Категория не найдена"));
        return CategoryMapper.mapCategoryToDto(category);
    }

    private void checkUniqueCatName(String name, Long id) {
        Category category = categoryRepository.findByName(name);
        if (category != null) {
            if (id == null || !id.equals(category.getId())) {
                throw new ConflictException("Категория с таким названием уже существует");
            }
        }
    }

}
