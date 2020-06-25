package com.study.u.service;

import com.study.u.dataobject.User;
import com.study.u.vo.PasswordVo;

public interface UserService {

    User findUserById(String id);

    User findUserByUsername(String username);

    String login(String username, String password);

    void saveUser(String username, String password, String token);

    void updateUserByUsername(String username, PasswordVo passwordVo);
}
