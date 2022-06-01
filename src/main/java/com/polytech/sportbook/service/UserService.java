package com.polytech.sportbook.service;

import com.polytech.sportbook.domain.Role;
import com.polytech.sportbook.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    List<Role> roles();

    void addRoleToUser(String userName, String roleName);

    User getUser(String userName);

    User getUserById(Long id);

    List<User> users();

    void deleteUser(Long id);

    User updateUser(Long id, User user);
}
