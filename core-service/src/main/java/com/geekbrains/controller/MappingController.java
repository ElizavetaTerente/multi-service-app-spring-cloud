package com.geekbrains.controller;

import com.geekbrains.model.OrderDto;
import com.geekbrains.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MappingController {
    @Autowired
    private MappingService mappingService;

    @GetMapping("/user/orderDetails/{id}")
    public List<OrderDto> productsInOrder(@PathVariable Long id){
        return mappingService.getOrderDto(id);
    }
}
