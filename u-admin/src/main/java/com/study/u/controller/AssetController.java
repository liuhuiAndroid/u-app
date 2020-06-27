package com.study.u.controller;

import com.study.u.annotation.UserLoginToken;
import com.study.u.dataobject.Asset;
import com.study.u.result.Result;
import com.study.u.service.AssetService;
import com.study.u.vo.UserVo;
import com.study.u.vo.WithdrawVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    AssetService assetService;

    /**
     * 查询资产
     */
    @UserLoginToken
    @PostMapping(value = "/find")
    public Result<Asset> addOrder(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        Asset asset = assetService.findByUsername(username);
        return Result.success(asset);
    }

    /**
     * 提现
     */
    @UserLoginToken
    @PostMapping(value = "/withdraw")
    public Result<String> withdraw(@Valid @RequestBody WithdrawVo withdrawVo, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        assetService.withdraw(username, withdrawVo);
        return Result.success("提现审核中");
    }

}
