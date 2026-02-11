package com.fsse2510.lab_project_backend.mapper;

import com.fsse2510.lab_project_backend.data.product_category.domainObject.response.ProductCategoryResponseData;
import com.fsse2510.lab_project_backend.data.product_category.dto.response.ProductCategoryResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCategoryDtoMapper {
    public ProductCategoryResponseDto toProductCategoryResponseDto(ProductCategoryResponseData productCategoryResponseData){
        ProductCategoryResponseDto productCategoryResponseDto = new ProductCategoryResponseDto();
        productCategoryResponseDto.setId(productCategoryResponseData.getId());
        productCategoryResponseDto.setCategory(productCategoryResponseData.getCategory());
        productCategoryResponseDto.setPid(productCategoryResponseData.getPid());
        return productCategoryResponseDto;
    }
    public List<ProductCategoryResponseDto> toProductCategoryResponseDtoList(List<ProductCategoryResponseData> productCategoryResponseDataList){
        List<ProductCategoryResponseDto> productCategoryResponseDtoList = new ArrayList<>();
        for (ProductCategoryResponseData productCategoryResponseData : productCategoryResponseDataList){
            productCategoryResponseDtoList.add(toProductCategoryResponseDto(productCategoryResponseData));
        }
        return productCategoryResponseDtoList;
    }
}
