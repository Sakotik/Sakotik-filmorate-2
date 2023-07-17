package ru.yandex.practicum.filmorate.storage.mpa_storage;

import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Map;

public interface MpaStorage {
    Mpa getMpa(int id);

    Map<Integer, Mpa> getMpa();
}
