package com.fsse2510.lab_project_backend.data.cart_item.dto.response;

import com.fsse2510.lab_project_backend.data.productImage.dto.response.ProductImageResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartItemResponseDto {
    private Integer pid;
    private String name;
    private List<ProductImageResponseDto> images;
    private BigDecimal price;
    private Integer cartQuantity;
    private Integer stock;
}
