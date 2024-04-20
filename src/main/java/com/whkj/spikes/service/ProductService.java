package com.whkj.spikes.service;


import com.whkj.spikes.dao.dataobject.ProductDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductService productService;

    public void updateProduct(ProductDO productDO){
        //修改库存

    }
}
