package com.springcloud.shoporder.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.springcloud.shoporder.feignservice.FeignProductService;
import com.springcloud.shoporder.service.OrderService;
import com.springcloud.shopuser.entity.ShopOrder;
import com.springcloud.shopuser.entity.ShopProduct;
import com.springcloud.shopuser.entity.ShopUser;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author fr
 * @Date 2020/7/30 14:09
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient client;
    @Resource
    private FeignProductService prductService;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 通过restTemplate，以网址的形式 请求调用远程服务
     * @param userName 用户名
     * @param pname 商品名
     */
    @PostMapping("/buy")
    public void addOrder(@RequestParam("userName")String userName,
                         @RequestParam("Pname")String pname){
        ShopOrder order = new ShopOrder();
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("name",userName);
        HttpEntity<MultiValueMap<String, String>> encapsulation = encapsulation(map);
        ResponseEntity<ShopUser> shopUserResponseEntity = restTemplate.postForEntity("http://localhost:8091/user/findByName", encapsulation, ShopUser.class);
        ShopUser body = shopUserResponseEntity.getBody();
        ShopProduct[] list = restTemplate.getForObject("http://localhost:8091/product/findByName/" + pname, ShopProduct[].class,pname);
        List<ShopProduct> shopProducts = Arrays.asList(list);
        for (ShopProduct product:shopProducts) {
            order.setUid(body.getUid());
            order.setShopName(product.getPname());
            service.addOrder(order);
        }
        for (ShopProduct p:shopProducts) {
            MultiValueMap<String,Object> maps = new LinkedMultiValueMap<>();
            maps.add("name",p.getPname());
            maps.add("stock",Integer.valueOf(p.getStock()-1));
            HttpEntity<MultiValueMap<String, Object>> encapsulation1 = encapsulation1(maps);
            restTemplate.postForEntity("http://localhost:8091/product/update",encapsulation1,null);
        }

    }

    /**
     * 集成nacos后，用nacos来发现服务，用restTemplate远程调用
     * @param userName
     * @param pname
     */
    @PostMapping("/orderBuy")
    public void buy(@RequestParam("userName")String userName,
                    @RequestParam("Pname")String pname){
        ShopOrder order = new ShopOrder();
        ServiceInstance serviceInstance = client.getInstances("product").get(0);
        String url = serviceInstance.getHost()+":"+serviceInstance.getPort();
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("name",userName);
        HttpEntity<MultiValueMap<String, String>> encapsulation = encapsulation(map);
        ResponseEntity<ShopUser> shopUserResponseEntity = restTemplate.postForEntity("http://"+url+"/user/findByName", encapsulation, ShopUser.class);
        ShopUser body = shopUserResponseEntity.getBody();

        ShopProduct[] list = restTemplate.getForObject("http://"+url+"/product/findByName/" + pname, ShopProduct[].class,pname);
        List<ShopProduct> shopProducts = Arrays.asList(list);
        for (ShopProduct product:shopProducts) {
            order.setUid(body.getUid());
            order.setShopName(product.getPname());
            service.addOrder(order);
        }
        for (ShopProduct p:shopProducts) {
            MultiValueMap<String,Object> maps = new LinkedMultiValueMap<>();
            maps.add("name",p.getPname());
            maps.add("stock",Integer.valueOf(p.getStock()-1));
            HttpEntity<MultiValueMap<String, Object>> encapsulation1 = encapsulation1(maps);
            restTemplate.postForEntity("http://"+url+"/product/update",encapsulation1,null);
        }

    }

    /**
     * 用服务名直接调用
     * @param userName
     * @param pname
     */
    @PostMapping("/buyPhone")
    public void orderBuy(@RequestParam("userName")String userName,
                    @RequestParam("Pname")String pname){
        ShopOrder order = new ShopOrder();
        ServiceInstance serviceInstance = client.getInstances("product").get(0);
        System.out.println(serviceInstance.getHost() +":"+ serviceInstance.getPort());
        String url = "product";
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("name",userName);
        HttpEntity<MultiValueMap<String, String>> encapsulation = encapsulation(map);
        ResponseEntity<ShopUser> shopUserResponseEntity = restTemplate.postForEntity("http://"+url+"/user/findByName", encapsulation, ShopUser.class);
        ShopUser body = shopUserResponseEntity.getBody();

        ShopProduct[] list = restTemplate.getForObject("http://"+url+"/product/findByName/" + pname, ShopProduct[].class,pname);
        List<ShopProduct> shopProducts = Arrays.asList(list);
        for (ShopProduct product:shopProducts) {
            order.setUid(body.getUid());
            order.setShopName(product.getPname());
            service.addOrder(order);
        }
        for (ShopProduct p:shopProducts) {
            MultiValueMap<String,Object> maps = new LinkedMultiValueMap<>();
            maps.add("name",p.getPname());
            maps.add("stock",Integer.valueOf(p.getStock()-1));
            HttpEntity<MultiValueMap<String, Object>> encapsulation1 = encapsulation1(maps);
            restTemplate.postForEntity("http://"+url+"/product/update",encapsulation1,null);
        }

    }

    @SentinelResource(value = "findAll")
    @RequestMapping(value = "/findAllPro",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ShopProduct> findAllProduct(){
        List<ShopProduct> allProduct = prductService.findAllProduct();
        if(allProduct==null){
            List<ShopProduct> productList = new ArrayList<>();
            ShopProduct product = new ShopProduct();
            product.setPname("下单失败");
            productList.add(product);
            return productList;
        }
        return allProduct;
    }

    /**
     * 通过feign实现手机的买卖
     * @param userName
     * @param Phone
     */
    @RequestMapping(value = "/buyPhones",method = RequestMethod.POST)
    public void buyPhone(@RequestParam("name")String userName,
                         @RequestParam("phone")String Phone){
        List<ShopProduct> productByName = prductService.findProductByName(Phone);
        ShopUser userByName = prductService.findUserByName(userName);
        for (ShopProduct sh:productByName) {
            prductService.updateProduct(sh.getPname(),sh.getStock()-1);
        }

        for (ShopProduct p :productByName) {
            ShopOrder order = new ShopOrder();
            order.setShopName(p.getPname());
            order.setUid(userByName.getUid());
            service.addOrder(order);
            rocketMQTemplate.convertAndSend("shop-order",order);
        }

    }



    private HttpEntity<MultiValueMap<String,String>>encapsulation(MultiValueMap<String,String> map){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(map,httpHeaders);
        return httpEntity;
    }

    private HttpEntity<MultiValueMap<String,Object>>encapsulation1(MultiValueMap<String,Object> map){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String,Object>> httpEntity = new HttpEntity<>(map,httpHeaders);
        return httpEntity;
    }





}
