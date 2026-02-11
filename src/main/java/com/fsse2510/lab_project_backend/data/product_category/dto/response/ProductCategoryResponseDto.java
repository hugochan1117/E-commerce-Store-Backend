package com.fsse2510.lab_project_backend.data.product_category.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProductCategoryResponseDto {
    private Integer id;
    private String category;
    private Integer pid;
}
