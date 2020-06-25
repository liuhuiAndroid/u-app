package com.study.u.repository;

import com.study.u.UApplicationTests;
import com.study.u.dataobject.Order;
import com.study.u.utils.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderRepositoryTest extends UApplicationTests {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void addOrder() {
        Order order = new Order();
        order.setId(UUIDUtil.uuid());
        order.setUsername("123123");
        order.setInvestAmount(12);
        order.setProfitAmount(13);
        order.setStartTime("4");
        order.setEndTime("78");
        order.setIsWithdraw(12);
        orderRepository.save(order);
    }

    @Test
    public void findAll() {
        List<Order> orders = orderRepository.findAll();
    }

}