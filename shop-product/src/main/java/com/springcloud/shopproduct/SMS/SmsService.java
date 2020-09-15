package com.springcloud.shopproduct.SMS;

import com.alibaba.fastjson.JSON;
import com.springcloud.shopuser.entity.ShopOrder;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @Author fr
 * @Date 2020/8/12 15:54
 */
@Service
@RocketMQMessageListener(consumerGroup = "shop-product",topic = "shop-order")
public class SmsService implements RocketMQListener<ShopOrder> {
    @Override
    public void onMessage(ShopOrder message) {
        System.out.println(JSON.toJSONString(message));
    }
}
