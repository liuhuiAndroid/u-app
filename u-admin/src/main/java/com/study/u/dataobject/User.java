package com.study.u.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    private String id;

    /** 账号. */
    private String username;

    /** 密码. */
    private String password;

    /** 令牌. */
    private String token;

    /** 手机号. */
    private String phone;

    /** 头像. */
    private String avatar;

    /** 是否删除. 1：删除 */
    private Integer is_delete;

    /** 是否锁定. 1：锁定 */
    private Integer is_lock;

}
