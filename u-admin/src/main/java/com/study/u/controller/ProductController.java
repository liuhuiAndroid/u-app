package com.study.u.controller;

import com.study.u.annotation.UserLoginToken;
import com.study.u.dataobject.Product;
import com.study.u.result.Result;
import com.study.u.service.ProductService;
import com.study.u.validator.ValidAnn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * 商品列表
     */
    @ValidAnn
    @PostMapping(value = "/list")
    public Result<List<Product>> productList() {
        List<Product> productList = productService.findAll();
        return Result.success(productList);
    }

}
