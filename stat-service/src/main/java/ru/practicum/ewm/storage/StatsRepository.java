package ru.practicum.ewm.storage;

import dto.IVewStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.ewm.model.Stats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<Stats, Long> {

    @Query(
            value =
                    "SELECT app, uri, COUNT(*) AS hits " +
                            "FROM stats " +
                            "WHERE created BETWEEN :start AND :end " +
                            "GROUP BY app, uri " +
                            "ORDER BY hits DESC",
            nativeQuery = true)
    public List<IVewStats> getAllStatsByPer(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    @Query(
            value =
                    "SELECT app, uri, COUNT(*) AS hits " +
                            "FROM stats " +
                            "WHERE created BETWEEN :start AND :end " +
                            " AND uri IN (:uris) " +
                            "GROUP BY app, uri " +
                            "ORDER BY hits DESC",
            nativeQuery = true)
    public List<IVewStats> getAllStatsByPerAndUris(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("uris") List<String> uris);

    @Query(
            value =
                    "WITH q AS ( " +
                            "SELECT DISTINCT app, uri, ip " +
                            "FROM stats " +
                            "WHERE created BETWEEN :start AND :end " +
                            ") " +
                        "SELECT app, uri, COUNT(*) AS hits " +
                        "FROM q " +
                        "GROUP BY app, uri " +
                        "ORDER BY hits DESC",
            nativeQuery = true)
    public List<IVewStats> getUniqueStatsByPer(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    @Query(
            value =
                    "WITH q AS ( " +
                            "SELECT DISTINCT app, uri, ip " +
                            "FROM stats " +
                            "WHERE created BETWEEN :start AND :end " +
                            " AND uri IN (:uris) " + //
                            ") " +
                            "SELECT app, uri, COUNT(*) AS hits " +
                            "FROM q " +
                            "GROUP BY app, uri " +
                            "ORDER BY hits DESC",
            nativeQuery = true)
    public List<IVewStats> getUniqueStatsByPerAndUris(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("uris") List<String> uris);

}
