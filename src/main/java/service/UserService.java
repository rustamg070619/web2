package service;

import model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class UserService {

    private static UserService INSTANCE;

    public UserService() {
    }

    public static UserService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }

    /* хранилище данных */
    private Map<Long, User> dataBase = Collections.synchronizedMap(new HashMap<>());
    /* счетчик id */
    private AtomicLong maxId = new AtomicLong(0);
    /* список авторизованных пользователей */
    private Map<Long, User> authMap = Collections.synchronizedMap(new HashMap<>());

    public List<User> getAllUsers() {
        return new ArrayList<>(dataBase.values());
    }

    public User getUserById(Long id) {
        return dataBase.get(id);
    }

    public boolean addUser(User user) {
        if (dataBase.containsKey(user.getId())) {
            return false;
        } else {
            user.setId(maxId.incrementAndGet());
            dataBase.put(maxId.longValue(), user);
            return true;
        }
    }

    public void deleteAllUser() {
        dataBase.clear();
    }

    public boolean isExistsThisUser(User user) {
        return dataBase.containsKey(user.getId());
    }

    public List<User> getAllAuth() {
        return new ArrayList<>(authMap.values());
    }

    public boolean authUser(User user) {
        if (dataBase.containsValue(user)) {
            user.setId(maxId.longValue());
            authMap.put(maxId.incrementAndGet(), user);
            return true;
        } else
            return false;
    }

    public void logoutAllUsers() {
        authMap.clear();
    }

    public boolean isUserAuthById(Long id) {
        return authMap.containsKey(id);
    }

}
