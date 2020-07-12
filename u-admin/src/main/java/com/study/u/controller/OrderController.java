package com.study.u.controller;

import com.study.u.annotation.UserLoginToken;
import com.study.u.dataobject.Order;
import com.study.u.result.Result;
import com.study.u.service.OrderService;
import com.study.u.validator.ValidAnn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * 订单列表
     */
    @ValidAnn
    @PostMapping(value = "/list")
    public Result<List<Order>> orderList() {
        List<Order> productList = orderService.findAll();
        return Result.success(productList);
    }

    /**
     * 下单
     */
    @UserLoginToken
    @PostMapping(value = "/add")
    public Result<String> addOrder(@RequestParam("productId") int productId, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        orderService.addOrder(username, productId);
        return Result.success("下单成功");
    }

    /**
     * 审核订单
     */
    @PostMapping(value = "/modifyStatus")
    public Result<String> modifyStatus(@RequestParam("orderId") String orderId) {
        orderService.modifyStatus(orderId);
        return Result.success("修改订单状态成功");
    }

}
