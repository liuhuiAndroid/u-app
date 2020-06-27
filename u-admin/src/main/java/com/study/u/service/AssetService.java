package com.study.u.service;

import com.study.u.dataobject.Asset;
import com.study.u.vo.WithdrawVo;

public interface AssetService {

    Asset findByUsername(String username);

    void withdraw(String username, WithdrawVo withdrawVo);
}
