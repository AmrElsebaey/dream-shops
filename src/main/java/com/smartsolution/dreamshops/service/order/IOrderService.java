package com.smartsolution.dreamshops.service.order;

import com.smartsolution.dreamshops.dto.OrderDto;
import com.smartsolution.dreamshops.model.Order;

import java.util.List;

public interface IOrderService {

    Order placeOrder(Long userId);
    Order getOrderById(Long orderId);

    List<Order> getOrdersByUserId(Long userId);

    OrderDto convertToDto(Order order);

    List<OrderDto> convertToDtoList(List<Order> orders);
}
