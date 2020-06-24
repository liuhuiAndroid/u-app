package com.study.u.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.study.u.Constants;
import com.study.u.result.CodeMsg;
import com.study.u.result.Result;
import com.study.u.service.UserService;
import com.study.u.utils.JwtUtil;
import com.study.u.validator.ValidAnn;
import com.study.u.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户注册
     */
    @PostMapping(value = "/login")
    public Result<String> login(@Valid @RequestBody UserVo userVo) {
        String token = userService.login(userVo.getUsername(), userVo.getPassword());
        return Result.success(token);
    }

    /**
     * 用户注册
     */
    @ValidAnn
    @PostMapping(value = "/register")
    public Result<String> register(@Valid @RequestBody UserVo userVo) {
        String token = JwtUtil.generateToken(userVo.getUsername());
        userService.saveUser(userVo.getUsername(), userVo.getPassword(), token);
        return Result.success(token);
    }

//        String userId = JWT.decode(token).getAudience().get(0);
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(Constants.secret)).build();
//        jwtVerifier.verify(token);

}
