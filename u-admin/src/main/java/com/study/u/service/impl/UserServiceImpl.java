package com.study.u.service.impl;

import com.study.u.dataobject.Asset;
import com.study.u.dataobject.User;
import com.study.u.exception.GlobalException;
import com.study.u.repository.AssetRepository;
import com.study.u.repository.UserRepository;
import com.study.u.result.CodeMsg;
import com.study.u.service.UserService;
import com.study.u.utils.JwtUtil;
import com.study.u.utils.UUIDUtil;
import com.study.u.vo.PasswordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AssetRepository assetRepository;

    @Override
    public User findUserById(String id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public String login(String username, String password) {
        User byUsername = userRepository.findByUsername(username);
        if (byUsername != null) {
            String token = JwtUtil.generateToken(username);
            byUsername.setToken(token);
            userRepository.saveAndFlush(byUsername);
            return token;
        } else {
            throw new GlobalException(CodeMsg.USERNAME_OR_PASSWORD_ERROR);
        }
    }

    @Override
    @Transactional
    public void saveUser(String username, String password, String token) {
        User byUsername = userRepository.findByUsername(username);
        if (byUsername != null) {
            throw new GlobalException(CodeMsg.USERNAME_IS_EXIST);
        }
        User user = new User();
        user.setId(UUIDUtil.uuid());
        user.setUsername(username);
        user.setPassword(password);
        user.setToken(token);
        userRepository.save(user);

        Asset asset = new Asset();
        asset.setId(UUIDUtil.uuid());
        asset.setUsername(username);
        asset.setInvest(0);
        asset.setExtend(0);
        asset.setAllUsdt(0);
        assetRepository.save(asset);
    }

    @Override
    public void updateUserByUsername(String username, PasswordVo passwordVo) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new GlobalException(CodeMsg.USERNAME_IS_NOT_EXIST);
        }
        if (user.getPassword().equals(passwordVo.getOldPassword())) {
            user.setPassword(passwordVo.getNewPassword());
            userRepository.save(user);
        } else {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
    }

}
