package ru.practicum.ewm.dto.compilation;

import lombok.Data;
import ru.practicum.ewm.dto.event.EventShortDto;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class NewCompilationDto {

    private List<Long> events;

    private Boolean pinned;

    @Size(min = 1, max = 50)
    private String title;

}
