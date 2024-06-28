package ru.practicum.ewm.controllers.compilation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.compilation.CompilationDto;
import ru.practicum.ewm.dto.compilation.UpdateCompilationRequest;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/admin/compilations")
@Slf4j
public class CompilationAdminController {

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void /* CompilationDto */ addCompilation(
            @RequestBody @Valid UpdateCompilationRequest dto
    ) {

    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCompilation(
            @PathVariable Long compId
    ) {

    }

    @PatchMapping("/{compId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void /* CompilationDto */ updateCompilation(
            @PathVariable Long compId,
            @RequestBody @Valid UpdateCompilationRequest dto
    ) {

    }

}
