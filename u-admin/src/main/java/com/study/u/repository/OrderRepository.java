package com.study.u.repository;

import com.study.u.dataobject.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

}

