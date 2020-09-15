package com.springcloud.shopproduct.service;

import com.springcloud.shopuser.entity.ShopUser;

import java.util.List;

/**
 * @Author fr
 * @Date 2020/7/30 10:04
 */
public interface UserService {
    List<ShopUser> searchAll();
    List<ShopUser> searchAll2();
    void addUser(ShopUser user);
    ShopUser findByName(String name);

}
