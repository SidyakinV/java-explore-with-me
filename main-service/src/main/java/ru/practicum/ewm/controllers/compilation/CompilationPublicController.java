package ru.practicum.ewm.controllers.compilation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/compilations")
@Slf4j
public class CompilationPublicController {

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void /*CompilationDto*/ getAllCompilations(
            @RequestParam Boolean pinned,
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size
    ) {

    }

    @GetMapping("/{compId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void /*CompilationDto*/ GetCompilationById(
            @PathVariable Long compId
    ) {

    }

}
