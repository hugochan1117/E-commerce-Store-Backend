package com.fsse2510.lab_project_backend.data.transaction_product.dto.response;

import com.fsse2510.lab_project_backend.data.product.dto.response.ProductResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class TransactionProductResponseDto {
    private Integer tid;
    private ProductResponseDto product;
    private Integer quantity;
    private BigDecimal subtotal;

}
