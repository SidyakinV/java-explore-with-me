package ru.practicum.ewm.controllers.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.services.CategoryService;
import ru.practicum.ewm.utility.PageCalc;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryPublicController {

    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<CategoryDto> getAllCategories(
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        log.info("GET-request '/categories' with parameters: from {}, size {}", from, size);
        return categoryService.getAllCategories(PageCalc.getPageable(from, size));
    }

    @GetMapping("/{catId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CategoryDto getCategoryById(
            @PathVariable Long catId
    ) {
        log.info("GET-request '/categories/{}'", catId);
        return categoryService.getCategoryById(catId);
    }

}
