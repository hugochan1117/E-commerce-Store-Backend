package com.fsse2510.lab_project_backend.mapper;

import com.fsse2510.lab_project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2510.lab_project_backend.data.product.entity.ProductEntity;
import com.fsse2510.lab_project_backend.data.productImage.domainObject.response.ProductImageResponseData;
import com.fsse2510.lab_project_backend.data.productImage.entity.ProductImageEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDataMapper {

    private final ProductCategoryDataMapper productCategoryDataMapper;

    public ProductDataMapper(ProductCategoryDataMapper productCategoryDataMapper) {
        this.productCategoryDataMapper = productCategoryDataMapper;
    }

    public ProductImageResponseData toProductImageResponseData(ProductImageEntity productImageEntity){
        ProductImageResponseData productImageResponseData = new ProductImageResponseData();
        productImageResponseData.setId(productImageEntity.getId());
        productImageResponseData.setImageUrl(productImageEntity.getImageUrl());
        productImageResponseData.setPid(productImageEntity.getProduct().getPid());
        return productImageResponseData;
    }
    public List<ProductImageResponseData> toProductImageResponseDataList(List<ProductImageEntity> productImageEntityList){
        List<ProductImageResponseData> productImageResponseDataList = new ArrayList<>();
        for (ProductImageEntity productImageEntity : productImageEntityList){
            ProductImageResponseData productImageResponseData = toProductImageResponseData(productImageEntity);
            productImageResponseDataList.add(productImageResponseData);
        }
        return productImageResponseDataList;
    }
    public ProductResponseData toProductResponseData(ProductEntity productEntity){
        ProductResponseData productResponseData = new ProductResponseData();
        productResponseData.setPid(productEntity.getPid());
        productResponseData.setName(productEntity.getName());
        productResponseData.setDescription(productEntity.getDescription());
        productResponseData.setProductImageList(toProductImageResponseDataList(productEntity.getImages()));
        productResponseData.setPrice(productEntity.getPrice());
        productResponseData.setStock(productEntity.getStock());
        productResponseData.setProductCategoryResponseDataList(productCategoryDataMapper.toProductCategoryResponseDataList(productEntity.getCategories()));
        productResponseData.setVariant(productEntity.getVariant());
        productResponseData.setIconVariable(productEntity.getIconVariable());
        productResponseData.setLinkedIds(productEntity.getLinkedIds());
        productResponseData.setVariantType(productEntity.getVariantType());
        return productResponseData;
    }
    public List<ProductResponseData> toProductResponseDataList(Iterable<ProductEntity> productEntityIterable){
        List<ProductResponseData> productResponseDataList = new ArrayList<>();
        for (ProductEntity productEntity : productEntityIterable){
            ProductResponseData productResponseData = toProductResponseData(productEntity);
            productResponseDataList.add(productResponseData);
        }
        return productResponseDataList;
    }
}
