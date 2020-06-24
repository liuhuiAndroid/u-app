package com.study.u.service;

public interface UserService {

    String login(String username, String password);

    void saveUser(String username, String password, String token);

}
