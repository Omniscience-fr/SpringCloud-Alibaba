package com.springcloud.shoporder.service.impl;

import com.springcloud.shoporder.mapper.OrderMapper;
import com.springcloud.shoporder.service.OrderService;
import com.springcloud.shopuser.entity.ShopOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author fr
 * @Date 2020/7/30 14:08
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper mapper;

    @Override
    public void addOrder(ShopOrder order) {
        mapper.addOrder(order);
    }

    @Override
    public void addOrder2(ShopOrder order) {
        mapper.addOrder2(order);
    }
}
