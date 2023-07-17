package ru.yandex.practicum.filmorate.storage.genre_storage;

import ru.yandex.practicum.filmorate.model.Genre;

import java.util.Map;

public interface GenreStorage {
    Genre getGenre(int id);

    Map<Integer, Genre> getGenres();
}
