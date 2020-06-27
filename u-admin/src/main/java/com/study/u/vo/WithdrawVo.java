package com.study.u.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class WithdrawVo {

    /**
     * 提现地址
     */
    @NotNull(message = "提现地址不能为空")
    private String address;

    /**
     * 提现金额
     */
    @NotNull(message = "提现金额不能为空")
    private Integer number;

}
