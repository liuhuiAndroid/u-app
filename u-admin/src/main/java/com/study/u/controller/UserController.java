package com.study.u.controller;

import com.study.u.annotation.UserLoginToken;
import com.study.u.result.Result;
import com.study.u.service.UserService;
import com.study.u.utils.JwtUtil;
import com.study.u.validator.ValidAnn;
import com.study.u.vo.PasswordVo;
import com.study.u.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户注册
     */
    @PostMapping(value = "/login")
    public Result<String> login(@Valid @RequestBody UserVo userVo, BindingResult bindingResult) {
        String token = userService.login(userVo.getUsername(), userVo.getPassword());
        return Result.success(token);
    }

    /**
     * 用户注册
     */
    @ValidAnn
    @PostMapping(value = "/register")
    public Result<String> register(@Valid @RequestBody UserVo userVo, BindingResult bindingResult) {
        String token = JwtUtil.generateToken(userVo.getUsername());
        userService.saveUser(userVo.getUsername(), userVo.getPassword(), token);
        return Result.success(token);
    }

    /**
     * 修改密码
     */
    @ValidAnn
    @UserLoginToken
    @PostMapping(value = "/modify/password")
    public Result<String> modifyPassword(@Valid @RequestBody PasswordVo passwordVo, BindingResult bindingResult, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        userService.updateUserByUsername(username, passwordVo);
        return Result.success("修改密码成功");
    }
}
