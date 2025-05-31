package com.smartsolution.dreamshops.dto;

import com.smartsolution.dreamshops.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto {

    private Long id;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private String orderStatus;
    private Long userId;
    private List<OrderItemDto> orderItems;

}
