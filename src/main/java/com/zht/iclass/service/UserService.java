package com.zht.iclass.service;

import com.zht.iclass.model.User;

public interface UserService {
    boolean addUser(User user);

    boolean updateUserByPhone(User user);

    boolean updateUserByUsername(User user);

    User selectUserByUsername(String username);

    User selectUserByPhone(String phone);

    boolean updatePasswordByPhone(String password,String phone);
}
