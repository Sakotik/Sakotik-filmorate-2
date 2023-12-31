package ru.yandex.practicum.filmorate.storage.user_storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {
    private final Map<Integer, User> users = new HashMap<>();
    private int idCounter = 0;

    @Override
    public User createUser(User user) {
        idCounter++;
        user.setId(idCounter);
        users.put(idCounter, user);
        log.info("Created " + user);
        System.out.println(users);
        return user;
    }

    @Override
    public User updateUser(User user) {
        users.put(user.getId(), user);
        log.info("Updated " + user);
        return user;
    }

    @Override
    public Map<Integer, User> getUsers() {
        log.info("Sent all users");
        return users;
    }
}
