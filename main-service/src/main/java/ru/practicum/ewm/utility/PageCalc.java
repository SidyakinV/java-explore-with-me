package ru.practicum.ewm.utility;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.practicum.ewm.exceptions.ValidationException;

public class PageCalc {

    public static Pageable getPageable(Integer from, Integer size) {
        return getPageable(from, size, Sort.unsorted());
    }

    public static Pageable getPageable(Integer from, Integer size, Sort sort) {
        if (from == null) {
            return Pageable.unpaged();
        }

        if (from < 0) {
            throw new ValidationException("Некорректное значение для стартового значения пагинации");
        }

        return PageRequest.of(from / size, size).withSort(sort);
    }

}
