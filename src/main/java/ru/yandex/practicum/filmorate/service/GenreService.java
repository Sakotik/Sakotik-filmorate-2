package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.genre_storage.GenreStorage;

import java.util.Map;

@Service
public class GenreService {
    @Autowired
    @Qualifier("dbStorage")
    private GenreStorage genreStorage;

    public Genre getGenre(int id) {
        Map<Integer, Genre> genres = genreStorage.getGenres();
        if (!genres.containsKey(id))
            throw new ObjectNotFoundException("Жанр с id=" + id + " не найден");
        return genres.get(id);
    }

    public Map<Integer, Genre> getGenres() {
        return genreStorage.getGenres();
    }
}
