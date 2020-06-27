package com.study.u.service.impl;

import com.study.u.dataobject.Asset;
import com.study.u.dataobject.Product;
import com.study.u.exception.GlobalException;
import com.study.u.repository.AssetRepository;
import com.study.u.repository.ProductRepository;
import com.study.u.result.CodeMsg;
import com.study.u.service.AssetService;
import com.study.u.service.ProductService;
import com.study.u.vo.WithdrawVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    AssetRepository assetRepository;

    @Override
    public Asset findByUsername(String username) {
        return assetRepository.findByUsername(username);
    }

    @Override
    public void withdraw(String username, WithdrawVo withdrawVo) {
        Asset asset = assetRepository.findByUsername(username);
        if (asset.getAllUsdt() > withdrawVo.getNumber()) {
            asset.setAllUsdt(asset.getAllUsdt() - withdrawVo.getNumber());
            assetRepository.save(asset);
        } else {
            throw new GlobalException(CodeMsg.ASSET_IS_NOT_ENOUGH);
        }
    }
}
