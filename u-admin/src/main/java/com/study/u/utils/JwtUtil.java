package com.study.u.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.study.u.Constants;
import com.study.u.exception.GlobalException;
import com.study.u.result.CodeMsg;

import java.util.Calendar;
import java.util.Date;

public class JwtUtil {

    public static String generateToken(String username){
        Calendar expireTime = Calendar.getInstance();
        expireTime.add(Calendar.DATE, 1);
        Date expiresDate = expireTime.getTime();
        String token = JWT.create()
                // 设置过期时间
                .withExpiresAt(expiresDate)
                // 设置接受方信息，一般时登录用户
                .withAudience(username)
                // 使用HMAC算法，u-admin作为密钥加密
                .sign(Algorithm.HMAC256(Constants.secret));
        return token;
    }

    public static String getUsername(String token){
        String userName;
        try {
            userName = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new GlobalException(CodeMsg.TOKEN_ERROR);
        }
        return userName;
    }

}
