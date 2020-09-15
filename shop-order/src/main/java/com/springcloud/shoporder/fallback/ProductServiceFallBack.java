package com.springcloud.shoporder.fallback;

import com.springcloud.shoporder.feignservice.FeignProductService;
import com.springcloud.shoporder.service.OrderService;
import com.springcloud.shopuser.entity.ShopOrder;
import com.springcloud.shopuser.entity.ShopProduct;
import com.springcloud.shopuser.entity.ShopUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author fr
 * @Date 2020/7/31 15:59
 */
@Component
public class ProductServiceFallBack implements FeignProductService {

    @Override
    public List<ShopProduct> findAllProduct() {
        return null;
    }

    @Override
    public List<ShopProduct> findProductByName(String name) {
        return null;
    }

    @Override
    public void updateProduct(String name, Integer stock) {

    }

    @Override
    public ShopUser findUserByName(String name) {
        return null;
    }
}
