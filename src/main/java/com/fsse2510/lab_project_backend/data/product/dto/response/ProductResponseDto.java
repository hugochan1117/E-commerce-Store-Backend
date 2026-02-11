package com.fsse2510.lab_project_backend.data.product.dto.response;

import com.fsse2510.lab_project_backend.data.productImage.dto.response.ProductImageResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductResponseDto {
    private Integer pid;
    private String name;
    private String description;
    private List<ProductImageResponseDto> productImageList;
    private BigDecimal price;
    private Integer stock;
    private String category;
    private String variantType;
    private List<Integer> linkedIds;
    private List<String> iconVariable;
    private List<String> variant;
}

