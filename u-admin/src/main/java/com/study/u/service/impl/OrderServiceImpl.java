package com.study.u.service.impl;

import com.study.u.dataobject.Order;
import com.study.u.dataobject.Product;
import com.study.u.exception.GlobalException;
import com.study.u.repository.OrderRepository;
import com.study.u.result.CodeMsg;
import com.study.u.service.OrderService;
import com.study.u.service.ProductService;
import com.study.u.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order addOrder(String username, int productId) {
        Optional<Order> orderOptional = orderRepository.findByUsernameAndIsWithdraw(username, 1);
        if (orderOptional.get() == null) {
            Product product = productService.findById(productId);
            Order order = new Order();
            order.setId(UUIDUtil.uuid());
            order.setUsername(username);
            order.setInvestAmount(product.getGainCondition());
            order.setProfitAmount((product.getGainCondition() * product.getGainCondition()) / 100);
            order.setStartTime(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, product.getTime());
            order.setEndTime(calendar.getTime());
            order.setIsWithdraw(1);
            orderRepository.save(order);
            return order;
        } else {
            throw new GlobalException(CodeMsg.ORDER_NOT_FINISH);
        }
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
