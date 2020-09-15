package com.springcloud.shopuser.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author fr
 * @Date 2020/7/30 09:33
 */

/**
 * 订单
 */
@Data
public class ShopOrder implements Serializable {
    /**
     * 订单id
     */
    private Long oid;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 商品名称
     */
    private String shopName;
}
