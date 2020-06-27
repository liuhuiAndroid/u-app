package com.study.u.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "u_asset")
public class Asset {

    @Id
    private String id;

    private String username;

    /** 投资资产. */
    private Integer invest;

    /** 推广资产.  */
    private Integer extend;

    /** 总资产.  */
    private Integer allUsdt;

}
