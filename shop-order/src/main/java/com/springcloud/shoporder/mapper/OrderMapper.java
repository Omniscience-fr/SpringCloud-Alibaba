package com.springcloud.shoporder.mapper;

import com.springcloud.shopuser.entity.ShopOrder;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @Author fr
 * @Date 2020/7/30 13:46
 */
@Repository
public interface OrderMapper {
    void addOrder(ShopOrder order);
    @Insert("insert into shop_order(uid,shopName) values(#{uid},#{shopName})")
    void addOrder2(ShopOrder order);


}
