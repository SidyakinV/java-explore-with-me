package ru.practicum.ewm.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventStorage {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    /*
    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("start", start)
                .addValue("end", end);

        StringBuilder sqlCmd = new StringBuilder(
                "WITH q AS ( \n" +
                        "  SELECT " + (unique ? "DISTINCT" : "") + " app, uri, ip \n" +
                        "  FROM stats \n" +
                        "  WHERE created BETWEEN :start AND :end \n");

        if (uris != null && uris.size() > 0) {
            sqlCmd.append("  AND uri IN (:uris) \n");
            parameters.addValue("uris", uris);
        }

        sqlCmd.append(
                ") \n" +
                        "SELECT app, uri, COUNT(*) AS hits \n" +
                        "FROM q \n" +
                        "GROUP BY app, uri \n" +
                        "ORDER BY hits DESC");

        return namedParameterJdbcTemplate.query(
                sqlCmd.toString(), parameters, this::mapRowToViewStats);
    }

    private ViewStats mapRowToViewStats(ResultSet rs, int rowNum) throws SQLException {
        return new ViewStats(
                rs.getString("app"),
                rs.getString("uri"),
                rs.getLong("hits")
        );
    }
    */

}