package com.springcloud.shopuser.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author fr
 * @Date 2020/7/30 09:26
 */

/**
 * 用户
 */
@Data
public class ShopUser implements Serializable {
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 电话
     */
    private Integer telephone;

}
