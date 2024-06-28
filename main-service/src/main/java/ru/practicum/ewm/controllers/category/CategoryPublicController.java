package ru.practicum.ewm.controllers.category;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/categories")
public class CategoryPublicController {

    @GetMapping
    public void /*List<CategoryDto>*/ getAllCategories(
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size
    ) {

    }

    @GetMapping("/{catId}")
    public void /*CategoryDto*/ getCategoryById(
            @PathVariable Long catId
    ) {

    }

}
