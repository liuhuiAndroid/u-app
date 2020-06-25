package com.study.u.service;

import com.study.u.dataobject.Order;
import com.study.u.dataobject.Product;

import java.util.List;

public interface OrderService {

    Order addOrder(String username, int productId);

}
