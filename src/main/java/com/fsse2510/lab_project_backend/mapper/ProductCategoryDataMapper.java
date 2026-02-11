package com.fsse2510.lab_project_backend.mapper;

import com.fsse2510.lab_project_backend.data.product_category.entity.ProductCategoryEntity;
import com.fsse2510.lab_project_backend.data.product_category.domainObject.response.ProductCategoryResponseData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCategoryDataMapper {
    public ProductCategoryResponseData toProductCategoryResponseData(ProductCategoryEntity productCategoryEntity){
        ProductCategoryResponseData productCategoryResponseData = new ProductCategoryResponseData();
        productCategoryResponseData.setId(productCategoryEntity.getId());
        productCategoryResponseData.setCategory(productCategoryEntity.getCategory());
        productCategoryResponseData.setPid(productCategoryEntity.getProduct().getPid());
        return productCategoryResponseData;

    }
    public List<ProductCategoryResponseData> toProductCategoryResponseDataList(List<ProductCategoryEntity> productCategoryEntityList){
        List<ProductCategoryResponseData> productCategoryResponseDataList = new ArrayList<>();
        for (ProductCategoryEntity productCategoryEntity : productCategoryEntityList){
            productCategoryResponseDataList.add(toProductCategoryResponseData(productCategoryEntity));
        }
        return productCategoryResponseDataList;
    }
}
