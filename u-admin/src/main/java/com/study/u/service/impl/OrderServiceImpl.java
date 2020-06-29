package com.study.u.service.impl;

import com.study.u.dataobject.Order;
import com.study.u.repository.OrderRepository;
import com.study.u.service.OrderService;
import com.study.u.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order addOrder(String username, int productId) {
        Order order = new Order();
        order.setId(UUIDUtil.uuid());
        order.setUsername(username);
        order.setInvestAmount(12);
        order.setProfitAmount(13);
        order.setStartTime("4");
        order.setEndTime("78");
        order.setIsWithdraw(12);
        orderRepository.save(order);
        return order;
    }

    @Override
    public void modifyStatus(String orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        Order order = orderOptional.get();
        if (order != null) {
            order.setIsWithdraw(1);
            orderRepository.save(order);
        }
    }

}
