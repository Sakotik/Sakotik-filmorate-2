package ru.yandex.practicum.filmorate.storage.film_storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Map;

public interface FilmStorage {
    Film createFilm(Film film);

    Film updateFilm(Film film);

    Film getFilm(int id);

    Map<Integer, Film> getFilms();
}
