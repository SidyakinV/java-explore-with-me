package ru.practicum.ewm.mappers;

import ru.practicum.ewm.dto.compilation.CompilationDto;
import ru.practicum.ewm.dto.compilation.NewCompilationDto;
import ru.practicum.ewm.dto.compilation.UpdateCompilationRequest;
import ru.practicum.ewm.models.Compilation;

import java.util.ArrayList;

import static ru.practicum.ewm.utility.FillFields.*;

public class CompilationMapper {

    public static Compilation mapNewCompDtoToCompilation(NewCompilationDto dto) {
        Compilation compilation = new Compilation();
        compilation.setTitle(dto.getTitle());
        compilation.setPinned(ifNull(dto.getPinned(), false));
        return compilation;
    }

    public static Compilation mapUpdateCompDtoToCompilation(UpdateCompilationRequest dto, Compilation oldCompilation) {
        Compilation compilation = new Compilation();
        compilation.setTitle(ifNull(dto.getTitle(), oldCompilation.getTitle()));
        compilation.setPinned(ifNull(dto.getPinned(), oldCompilation.getPinned()));
        return compilation;
    }

    public static CompilationDto mapCompilationToDto(Compilation compilation) {
        CompilationDto dto = new CompilationDto();
        dto.setEvents(new ArrayList<>());
        dto.setId(compilation.getId());
        dto.setTitle(compilation.getTitle());
        dto.setPinned(compilation.getPinned());
        return dto;
    }

}
