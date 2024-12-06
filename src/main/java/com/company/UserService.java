package com.company;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();

    public void addAdmin(long chatId, String userName) {
        users.add(new User(chatId, userName,Role.ADMIN));
    }

    public boolean isUserRegistered(long chatId) {
        return users.stream().anyMatch(user -> user.getTelegramId().equals(String.valueOf(chatId)));
    }

    public void registerUser(int chatId, String userName) {
        if (!isUserRegistered(chatId)) {
            users.add(new User(chatId, userName, Role.USER));
        }
    }

    public boolean isAdmin(long chatId) {
        return users.stream().anyMatch(user -> user.getTelegramId().equals(String.valueOf(chatId)) && user.getRole() == Role.ADMIN);
    }


    public List<User> getUsers() {
        return users;
    }
}
