package com.safecornerscoffee.msa.user.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseOrder {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date createdAt;
}
