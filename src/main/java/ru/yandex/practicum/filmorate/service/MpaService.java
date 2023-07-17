package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.mpa_storage.MpaStorage;

import java.util.Map;

@Service
public class MpaService {
    @Autowired
    @Qualifier("dbStorage")
    private MpaStorage mpaStorage;

    public Mpa getMpa(int id) {
        Map<Integer, Mpa> mpa = mpaStorage.getMpa();
        if (!mpa.containsKey(id))
            throw new ObjectNotFoundException("Рейтинг с id=" + id + " не найден");
        return mpa.get(id);
    }

    public Map<Integer, Mpa> getAllMpa() {
        return mpaStorage.getMpa();
    }
}
