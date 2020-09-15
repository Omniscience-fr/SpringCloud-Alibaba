package com.springcloud.shopproduct.service.impl;

import com.springcloud.shopproduct.mapper.ProductMapper;
import com.springcloud.shopproduct.service.ProductService;
import com.springcloud.shopuser.entity.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author fr
 * @Date 2020/7/30 11:31
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper mapper;

    @Override
    public void addProduct(ShopProduct product) {
        mapper.addProduct(product);
    }

    @Override
    public void addProduct2(ShopProduct product) {
        mapper.addProduct2(product);
    }

    @Override
    public List<ShopProduct> findAllProduct() {
        return mapper.findAll();
    }

    @Override
    public List<ShopProduct> findAllProduct2() {
        return mapper.findAll2();
    }

    @Override
    public List<ShopProduct> findByName(String name) {
        return mapper.findByName(name);
    }

    @Override
    public void updateProduct(String name, Integer stock) {
        mapper.UpdateProduct(name,stock);
    }
}
