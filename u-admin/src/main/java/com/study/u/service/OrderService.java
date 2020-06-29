package com.study.u.service;

import com.study.u.dataobject.Order;
import com.study.u.dataobject.Product;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order addOrder(String username, int productId);

    void modifyStatus(String orderId);
}
