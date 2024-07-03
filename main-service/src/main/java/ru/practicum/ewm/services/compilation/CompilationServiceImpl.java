package ru.practicum.ewm.services.compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.dto.compilation.CompilationDto;
import ru.practicum.ewm.dto.compilation.NewCompilationDto;
import ru.practicum.ewm.dto.compilation.UpdateCompilationRequest;
import ru.practicum.ewm.exceptions.NotFoundException;
import ru.practicum.ewm.mappers.CompilationMapper;
import ru.practicum.ewm.mappers.EventMapper;
import ru.practicum.ewm.models.CompEvent;
import ru.practicum.ewm.models.Compilation;
import ru.practicum.ewm.repositories.CompEventRepository;
import ru.practicum.ewm.repositories.CompilationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;
    private final CompEventRepository compEventRepository;

    @Override
    @Transactional
    public CompilationDto addCompilation(NewCompilationDto dto) {
        Compilation compilation = CompilationMapper.mapNewCompDtoToCompilation(dto);

        Compilation savedCompilation = compilationRepository.save(compilation);

        dto.getEvents().forEach(eventId -> {
            compEventRepository.insertCompEvent(savedCompilation.getId(), eventId);
        });

        log.info("Добавлена подборка {} со списком событий {}", savedCompilation, dto.getEvents());
        return getCompilationById(savedCompilation.getId());
    }

    @Override
    public void deleteCompilation(Long compId) {
        findCompilationById(compId);
        compilationRepository.deleteById(compId);
    }

    @Override
    @Transactional
    public CompilationDto updateCompilation(Long compId, UpdateCompilationRequest dto) {
        Compilation oldCompilation = findCompilationById(compId);
        Compilation newCompilation = CompilationMapper.mapUpdateCompDtoToCompilation(dto, oldCompilation);
        newCompilation.setId(compId);

        Compilation savedCompilation = compilationRepository.save(newCompilation);

        if (dto.getEvents() != null) {
            compEventRepository.deleteCompEvents(compId);
            dto.getEvents().forEach(eventId -> {
                compEventRepository.insertCompEvent(compId, eventId);
            });
        }

        log.info("Изменена подборка {} со списком событий {}", savedCompilation, dto.getEvents());
        return getCompilationById(compId);
    }

    @Override
    public List<CompilationDto> getAllCompilations(Boolean pinned, Pageable pageable) {
        List<Compilation> compilations = compilationRepository.findAllByPinned(pinned, pageable);

        List<CompilationDto> compilationDtoList = new ArrayList<>();
        compilations.forEach(compilation -> {
            compilationDtoList.add(fillCompilationDto(compilation));
        });

        return compilationDtoList;
    }

    @Override
    public CompilationDto getCompilationById(Long compId) {
        Compilation compilation = findCompilationById(compId);
        return fillCompilationDto(compilation);
    }

    private Compilation findCompilationById(Long compId) {
        return compilationRepository.findById(compId).orElseThrow(() ->
                new NotFoundException(String.format("Compilation with id=%d was not found", compId)));
    }

    private CompilationDto fillCompilationDto(Compilation compilation) {
        CompilationDto dto = CompilationMapper.mapCompilationToDto(compilation);

        List<CompEvent> compEvents = compEventRepository.findAllByCompilationId(compilation.getId());
        compEvents.forEach(compEvent -> {
            dto.getEvents().add(EventMapper.mapEventToEventShortDto(compEvent.getEvent()));
        });

        return dto;
    }

}
