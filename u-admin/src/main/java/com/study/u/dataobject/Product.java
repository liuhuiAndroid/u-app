package com.study.u.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "u_product")
public class Product {

    @Id
    private Integer id;

    /** 期限 单位天. */
    private Integer time;

    /** 期限中文描述. */
    private String time_desc;

    /** 收益百分比. */
    private Integer gain;

    /** 条件（U币个数）. */
    private Integer gainCondition;

}
