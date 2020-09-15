package com.springcloud.shoporder.service;

import com.springcloud.shopuser.entity.ShopOrder;

/**
 * @Author fr
 * @Date 2020/7/30 14:08
 */
public interface OrderService {
    void addOrder(ShopOrder order);

    void addOrder2(ShopOrder order);
}
