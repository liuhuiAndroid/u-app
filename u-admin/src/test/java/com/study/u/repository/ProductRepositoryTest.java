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

    @Test
    public void addProduct() {
        Product product = new Product();
        product.setId(111);
        product.setTime(10);
        product.setTime_desc("123");
        product.setGain(5);
        product.setGainCondition(5);
        productRepository.save(product);
    }

}