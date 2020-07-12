package com.study.u.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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
    private Date startTime;

    /**
     * 到期日期.
     */
    private Date endTime;

    /**
     * 是否提现. 0：未提现 1：提现中 2：已提现
     */
    private Integer isWithdraw;

    /**
     * 订单状态. 0：待审核 1：审核通过
     */
    private Integer status;

}
