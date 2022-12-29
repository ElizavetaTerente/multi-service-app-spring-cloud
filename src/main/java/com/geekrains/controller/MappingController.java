package com.geekrains.controller;

import com.geekrains.model.OrderDto;
import com.geekrains.model.Product;
import com.geekrains.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MappingController {
    @Autowired
    private MappingService mappingService;

    @GetMapping("/user/orderDetails/{id}")
    public List<OrderDto> productsInOrder(@PathVariable Long id){
        return mappingService.getOrderDto(id);
    }
}
