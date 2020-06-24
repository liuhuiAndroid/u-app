package com.study.u.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class UserVo {

    /**
     * 账号
     */
    @NotNull(message = "用户名不能为空")
    @Length(min = 6, message = "用户名不少于六位")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    @Length(min = 6, message = "密码不少于六位")
    private String password;

}
