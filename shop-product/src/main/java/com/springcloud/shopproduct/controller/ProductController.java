package com.springcloud.shopproduct.controller;

import com.springcloud.shopproduct.service.ProductService;
import com.springcloud.shopuser.entity.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author fr
 * @Date 2020/7/30 11:33
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @RequestMapping(value = "/insert",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public void InsertProduct(@RequestParam("pname")String name,
                              @RequestParam("price") Double price,
                              @RequestParam("stock") Integer stock){
        ShopProduct product = new ShopProduct();
        product.setPname(name);
        product.setPrice(price);
        product.setStock(stock);
        service.addProduct(product);
    }
    @GetMapping(value = "/add/{name}/{price}/{stock}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(@PathVariable("name")String name,
                           @PathVariable("price")Double price,
                           @PathVariable("stock")Integer stock){
        ShopProduct product = new ShopProduct();
        product.setPname(name);
        product.setPrice(price);
        product.setStock(stock);
        service.addProduct2(product);
    }

    @PostMapping(value = "/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShopProduct> findProductAll(){
        return service.findAllProduct();
    }

    @GetMapping(value = "/findProduct",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShopProduct> findAll(){
        return service.findAllProduct2();
    }

    @GetMapping(value = "/findByName/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShopProduct> findByName(@PathVariable("name")String name){
        return service.findByName(name);
    }

    @PostMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateproduct(@RequestParam("name")String name,
                              @RequestParam("stock")Integer stock){
        service.updateProduct(name,stock);

    }



}
