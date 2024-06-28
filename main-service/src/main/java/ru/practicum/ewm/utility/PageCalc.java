package ru.practicum.ewm.utility;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.practicum.ewm.exceptions.ValidationException;

public class PageCalc {

    public static Pageable getPageable(Integer from, Integer size) {
        if (from == null) {
            return Pageable.unpaged();
        } else if (from < 0) {
            throw new ValidationException("Некорректное значение");
        } else {
            return PageRequest.of(from / size, size);
        }
    }

}
