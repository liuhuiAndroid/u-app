package com.study.u.dataobject;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "u_order")
public class Order {

    @Id
    private String id;

    /**
     * 账号.
     */
    private String username;

    /**
     * 投资金额.
     */
    private Integer investAmount;

    /**
     * 盈利金额.
     */
    private Integer profitAmount;

    /**
     * 投资日期.
     */
    private String startTime;

    /**
     * 到期日期.
     */
    private String endTime;

    /**
     * 是否提现. 1：提现中 2：已提现
     */
    private Integer isWithdraw;

}