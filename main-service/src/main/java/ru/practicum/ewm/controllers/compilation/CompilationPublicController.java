package ru.practicum.ewm.controllers.compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.compilation.CompilationDto;
import ru.practicum.ewm.services.compilation.CompilationService;
import ru.practicum.ewm.utility.PageCalc;

import java.util.List;

@RestController
@RequestMapping(path = "/compilations")
@RequiredArgsConstructor
@Slf4j
public class CompilationPublicController {

    private final CompilationService compilationService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<CompilationDto> getAllCompilations(
            @RequestParam(defaultValue = "true") Boolean pinned,
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        log.info("GET-request '/compilations'");
        return compilationService.getAllCompilations(pinned, PageCalc.getPageable(from, size));
    }

    @GetMapping("/{compId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CompilationDto getCompilationById(
            @PathVariable Long compId
    ) {
        log.info("GET-request '/compilations/{}'", compId);
        return compilationService.getCompilationById(compId);
    }

}
