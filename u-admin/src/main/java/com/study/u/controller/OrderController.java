package com.study.u.controller;

import com.study.u.annotation.UserLoginToken;
import com.study.u.result.Result;
import com.study.u.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * 商品列表
     */
    @UserLoginToken
    @PostMapping(value = "/add")
    public Result addOrder(@RequestParam("productId") int productId, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        orderService.addOrder(username, productId);
        return Result.success();
    }

}
