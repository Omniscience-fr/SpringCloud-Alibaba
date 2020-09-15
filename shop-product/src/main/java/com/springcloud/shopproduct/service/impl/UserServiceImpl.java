package com.springcloud.shopproduct.service.impl;

import com.springcloud.shopproduct.mapper.UserMapper;
import com.springcloud.shopproduct.service.UserService;
import com.springcloud.shopuser.entity.ShopUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author fr
 * @Date 2020/7/30 10:05
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public List<ShopUser> searchAll() {
        return mapper.searchAll();
    }

    @Override
    public List<ShopUser> searchAll2() {
        return mapper.searchAll2();
    }

    @Override
    public void addUser(ShopUser user) {
        mapper.addUser(user);
    }

    @Override
    public ShopUser findByName(String name) {
        return mapper.findByName(name);
    }
}
