package com.springcloud.shoporder.feignservice;

import com.springcloud.shoporder.fallback.ProductServiceFallBack;
import com.springcloud.shopuser.entity.ShopProduct;
import com.springcloud.shopuser.entity.ShopUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author fr
 * @Date 2020/7/30 17:45
 */
@FeignClient(name = "product",fallback = ProductServiceFallBack.class)
//@FeignClient(name = "product")
@Service
//@RequestMapping("/FeignPro")
public interface FeignProductService {
//    @GetMapping("/product/findAll") // 必须和被调用的接口的请求方式一致
    @RequestMapping(value = "/product/findAll",method = RequestMethod.POST)
    @ResponseBody
    List<ShopProduct> findAllProduct();

    @RequestMapping(value = "/product/findByName/{name}",method = RequestMethod.GET)
    List<ShopProduct> findProductByName(@PathVariable String name);

    @RequestMapping(value = "/product/update",method = RequestMethod.POST)
    void updateProduct(@RequestParam String name,
                       @RequestParam Integer stock);

    @RequestMapping(value = "/user/findByName",method = RequestMethod.POST)
    ShopUser findUserByName(@RequestParam String name);

}
