package com.study.u.repository;

import com.study.u.UApplicationTests;
import com.study.u.dataobject.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductRepositoryTest extends UApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void findAll() {
        List<Product> productList = productRepository.findAll();
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).toString());
        }
    }

}