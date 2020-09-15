package com.springcloud.shopproduct.service;

import com.springcloud.shopuser.entity.ShopProduct;

import java.util.List;

/**
 * @Author fr
 * @Date 2020/7/30 11:29
 */
public interface ProductService {
    void addProduct(ShopProduct product);
    void addProduct2(ShopProduct product);
    List<ShopProduct> findAllProduct();
    List<ShopProduct> findAllProduct2();
    List<ShopProduct> findByName(String name);
    void updateProduct(String name,Integer stock);

}
