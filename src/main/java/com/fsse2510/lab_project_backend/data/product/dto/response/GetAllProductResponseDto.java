package com.fsse2510.lab_project_backend.data.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2510.lab_project_backend.data.productImage.dto.response.ProductImageResponseDto;
import com.fsse2510.lab_project_backend.data.product_category.dto.response.ProductCategoryResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
public class GetAllProductResponseDto {

    private Integer pid;
    private String name;
    @JsonProperty("images")
    private List<ProductImageResponseDto> images;
    private BigDecimal price;
    private boolean hasStock;
    private List<ProductCategoryResponseDto> categories;
    private String variantType;
    private List<Integer> linkedIds;
    private List<String> iconVariable;
    private List<String> variant;
}
