package com.springcloud.shopproduct.controller;

import com.springcloud.shopproduct.service.UserService;
import com.springcloud.shopuser.entity.ShopUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author fr
 * @Date 2020/7/30 10:07
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/find",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ShopUser> findAll(){
        return service.searchAll();
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestParam("username")String name,
                        @RequestParam("password")String pwd,
                        @RequestParam("phone")int phone){
        ShopUser user = new ShopUser();
        user.setUserName(name);
        user.setPassword(pwd);
        user.setTelephone(phone);
        service.addUser(user);
    }

    @PostMapping(value = "/findByName",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ShopUser findByName(@RequestParam("name")String name){
        return service.findByName(name);
    }

}
