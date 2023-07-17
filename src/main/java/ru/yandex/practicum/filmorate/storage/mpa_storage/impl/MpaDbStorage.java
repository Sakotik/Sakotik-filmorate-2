package ru.yandex.practicum.filmorate.storage.mpa_storage.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.mpa_storage.MpaStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Qualifier("dbStorage")
@Slf4j
public class MpaDbStorage implements MpaStorage {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Mpa> mpaMapper = new RowMapper<Mpa>() {
        @Override
        public Mpa mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("MPA_ID");
            String name = rs.getString("NAME");
            return new Mpa(id, name);
        }
    };

    public MpaDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Mpa getMpa(int id) {
        String sql = "SELECT * FROM MPA " +
                     "WHERE MPA_ID=?";
        List<Mpa> mpaList = jdbcTemplate.query(sql, mpaMapper);
        log.info("Sent mpa by id=" + id);
        return mpaList.get(0);
    }

    @Override
    public Map<Integer, Mpa> getMpa() {
        String sql = "SELECT * FROM MPA";
        List<Mpa> mpa = jdbcTemplate.query(sql, mpaMapper);
        log.info("Sent all mpa");
        return mpa.stream()
                .collect(Collectors.toMap(Mpa::getId, Function.identity()));
    }
}
