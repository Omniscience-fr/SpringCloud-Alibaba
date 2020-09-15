package com.springcloud.shopproduct.mapper;

import com.springcloud.shopuser.entity.ShopProduct;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author fr
 * @Date 2020/7/30 11:10
 */
@Repository
public interface ProductMapper {

    void addProduct(ShopProduct product);

    @Insert("insert into shop_product(pname,price,stock) values(#{pname},#{price},#{stock})")
    void addProduct2(ShopProduct product);

    List<ShopProduct> findAll();

    @Select("select * from shop_product")
    @Results({
            @Result(column = "pname",property = "pname",jdbcType = JdbcType.VARCHAR),
            @Result(column = "price",property = "price",jdbcType = JdbcType.DOUBLE),
            @Result(column = "stock",property = "stock",jdbcType = JdbcType.INTEGER)
    })
    List<ShopProduct> findAll2();

    List<ShopProduct> findByName(String name);

    @Update("update shop_product set stock=#{stock} where pname=#{name}")
    void UpdateProduct(String name,Integer stock);

}
