package ru.practicum.ewm.controllers.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.services.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/admin/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryAdminController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CategoryDto addCategory(
            @RequestBody @Valid CategoryDto dto
    ) {
        log.info("POST-request '/admin/categories' with parameters: dto {}", dto);
        return categoryService.addCategory(dto);
    }

    @PatchMapping("/{catId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CategoryDto updateCategory(
            @RequestBody @Valid CategoryDto dto,
            @PathVariable Long catId
    ) {
        log.info("PATCH-request '/admin/categories/{}' with parameters: dto {}", dto, catId);
        dto.setId(catId);
        return categoryService.updateCategory(dto);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(
            @PathVariable Long catId
    ) {
        log.info("DELETE-request '/admin/categories/{}'}", catId);
        categoryService.deleteCategory(catId);
    }

}
