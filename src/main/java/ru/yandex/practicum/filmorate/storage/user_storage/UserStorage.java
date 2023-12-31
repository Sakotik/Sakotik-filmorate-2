package ru.yandex.practicum.filmorate.storage.user_storage;

import ru.yandex.practicum.filmorate.model.User;


import java.util.Map;


public interface UserStorage {

    User createUser(User user);

    User updateUser(User user);

    Map<Integer, User> getUsers();
}
