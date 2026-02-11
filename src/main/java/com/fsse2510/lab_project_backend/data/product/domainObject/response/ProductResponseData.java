package com.fsse2510.lab_project_backend.data.product.domainObject.response;

import com.fsse2510.lab_project_backend.data.productImage.domainObject.response.ProductImageResponseData;
import com.fsse2510.lab_project_backend.data.product_category.domainObject.response.ProductCategoryResponseData;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
public class ProductResponseData {
    private Integer pid;
    private String name;
    private String description;
    private List<ProductImageResponseData> productImageList;
    private BigDecimal price;
    private Integer stock;
    private List<ProductCategoryResponseData> productCategoryResponseDataList;
    private String variantType;
    private List<Integer> linkedIds;
    private List<String> iconVariable;
    private List<String> variant;
}
