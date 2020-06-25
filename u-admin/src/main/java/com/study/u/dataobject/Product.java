package com.study.u.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Product {

    @Id
    private String id;

    /** 期限 单位天. */
    private int time;

    /** 期限中文描述. */
    private String time_desc;

    /** 收益. */
    private int gain;

    /** 条件. */
    private int condition;

}
