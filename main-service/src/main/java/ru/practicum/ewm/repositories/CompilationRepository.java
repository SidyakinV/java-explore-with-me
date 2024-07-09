package ru.practicum.ewm.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.models.Compilation;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {

    Slice<Compilation> findAllByPinned(Boolean pinned, Pageable pageable);

}
