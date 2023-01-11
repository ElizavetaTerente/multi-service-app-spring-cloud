package com.geekbrains.service;

import com.geekbrains.model.OrderDto;
import com.geekbrains.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MappingService {

    @Autowired
    OrderService orderService;

    public List<OrderDto> getOrderDto(Long id) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        Map<Product,Integer> productsInMap = orderService.findOrderById(id).getItemsToBuy();
        for (Map.Entry<Product,Integer> pim : productsInMap.entrySet()) {
            orderDtoList.add(convertOrderIntoDto(pim));
        }
        return orderDtoList;
    }

    private OrderDto convertOrderIntoDto(Map.Entry<Product,Integer> productMapEntryData) {

        OrderDto orderDto = new OrderDto();

        orderDto.setProductName(productMapEntryData.getKey().getTitle());
        orderDto.setCost(productMapEntryData.getKey().getCost());
        orderDto.setQuantity(productMapEntryData.getValue());
        orderDto.setGraphicalRepresentationOfAFullPrice(orderDto.makeGraphicalRepresentationOfAFullPrice());

        return orderDto;
    }
}
