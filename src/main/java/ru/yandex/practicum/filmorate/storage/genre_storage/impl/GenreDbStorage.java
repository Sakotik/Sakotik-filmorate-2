package ru.yandex.practicum.filmorate.storage.genre_storage.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.genre_storage.GenreStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Qualifier("dbStorage")
@Slf4j
public class GenreDbStorage implements GenreStorage {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Genre> genreMapper = new RowMapper<Genre>() {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("GENRE_ID");
            String name = rs.getString("NAME");
            return new Genre(id, name);
        }
    };

    public GenreDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Genre getGenre(int id) {
        String sql = "SELECT GENRE_ID, " +
                "NAME " +
                "FROM GENRE " +
                "WHERE GENRE_ID=?;";
        List<Genre> genreList = jdbcTemplate.query(sql, genreMapper);
        log.info("Sent genre by id=" + id);
        return genreList.get(0);
    }

    @Override
    public Map<Integer, Genre> getGenres() {
        String sql = "SELECT GENRE_ID, " +
                "NAME " +
                "FROM GENRE;";
        List<Genre> genres = jdbcTemplate.query(sql, genreMapper);
        log.info("Sent all genres");
        return genres.stream()
                .collect(Collectors.toMap(Genre::getId, Function.identity()));
    }
}
