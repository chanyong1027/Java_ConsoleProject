package service;

import domain.User;

import java.util.Optional;

public class SessionManager {
    private  User currentUser;

    public void login(User user) {
        this.currentUser = user;
    }

    public void logout() {
        this.currentUser = null;
    }

    public Optional<User> getCurrentUser() {
        return Optional.ofNullable(currentUser);
    }

    public  boolean isLoggedIn() {
        return currentUser != null;
    }
}
