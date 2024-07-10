package com.revature.repository;

import com.revature.Entity.User;

import java.util.List;

public interface UserDao {
// i can do this becuase interfaces already have public abstract secretly inserted.
    User createUser(User newUserCredentials);

    List<User> getAllUsers();
}
