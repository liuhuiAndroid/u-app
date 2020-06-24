package com.study.u.repository;

import com.study.u.UApplicationTests;
import com.study.u.dataobject.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends UApplicationTests {

    @Autowired
    UserRepository userRepository;

//    @Test
    public void saveUser() {
        User user = new User();
        user.setId("1");
        user.setUsername("seckill");
        user.setPassword("secpwd");
        user.setPhone("phone");
        user.setAvatar("avatar");
        userRepository.save(user);
    }

}
