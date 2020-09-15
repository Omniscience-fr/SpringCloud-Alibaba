package com.springcloud.shopuser.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author fr
 * @Date 2020/7/30 09:29
 */

/**
 * 商品
 */
@Data
public class ShopProduct implements Serializable {
    /**
     *商品id
     */
    private Integer pid;
    /**
     * 商品名称
     */
    private String pname;
    /**
     * 商品价格
     */
    private Double price;
    /**
     * 库存
     */
    private Integer stock;
}
