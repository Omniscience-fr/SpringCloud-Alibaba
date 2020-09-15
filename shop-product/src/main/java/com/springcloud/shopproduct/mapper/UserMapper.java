package com.springcloud.shopproduct.mapper;

import com.springcloud.shopuser.entity.ShopUser;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author fr
 * @Date 2020/7/30 10:02
 */
@Repository
public interface UserMapper {
    /**
     * 注解形式实现mybatis
     * @return
     */
    @Select("select * from shop_user")
    @Results({
                    @Result(property = "uid", column = "uid", jdbcType = JdbcType.INTEGER),
                    @Result(property = "userName", column = "userName", jdbcType = JdbcType.VARCHAR),
                    @Result(property = "password", column = "password", jdbcType = JdbcType.VARCHAR),
                    @Result(property = "telephone", column = "telephone", jdbcType = JdbcType.INTEGER)
            })
    List<ShopUser> searchAll2();

    /**
     * xml形式实现mybatis
     * @return
     */
    List<ShopUser> searchAll();

    void addUser(ShopUser user);

    ShopUser findByName(String name);
}
