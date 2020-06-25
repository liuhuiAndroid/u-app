package com.study.u.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class PasswordVo {

    /**
     * 旧密码
     */
    @NotNull(message = "旧密码不能为空")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotNull(message = "新密码不能为空")
    @Length(min = 6, message = "新密码不少于六位")
    private String newPassword;

}
