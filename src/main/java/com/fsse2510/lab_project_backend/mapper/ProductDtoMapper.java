package com.fsse2510.lab_project_backend.mapper;

import com.fsse2510.lab_project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2510.lab_project_backend.data.product.dto.response.GetAllProductResponseDto;
import com.fsse2510.lab_project_backend.data.product.dto.response.ProductResponseDto;
import com.fsse2510.lab_project_backend.data.productImage.domainObject.response.ProductImageResponseData;
import com.fsse2510.lab_project_backend.data.productImage.dto.response.ProductImageResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDtoMapper {

    private final ProductCategoryDtoMapper productCategoryDtoMapper;

    public ProductDtoMapper(ProductCategoryDtoMapper productCategoryDtoMapper) {
        this.productCategoryDtoMapper = productCategoryDtoMapper;
    }

    public ProductImageResponseDto toProductImageResponseDto(ProductImageResponseData productImageResponseData){
        ProductImageResponseDto productImageResponseDto = new ProductImageResponseDto();
        productImageResponseDto.setId(productImageResponseData.getId());
        productImageResponseDto.setImageUrl(productImageResponseData.getImageUrl());
        productImageResponseDto.setPid(productImageResponseData.getPid());
        return productImageResponseDto;
    }
    public List<ProductImageResponseDto> toProductImageResponseDtoList(List<ProductImageResponseData> productImageResponseDataList){
        List<ProductImageResponseDto> productImageResponseDtoList = new ArrayList<>();
        for (ProductImageResponseData productImageResponseData : productImageResponseDataList){
            ProductImageResponseDto productImageResponseDto = toProductImageResponseDto(productImageResponseData);
            productImageResponseDtoList.add(productImageResponseDto);
        }
        return productImageResponseDtoList;
    }
    public GetAllProductResponseDto getAllProductResponseDto(ProductResponseData productResponseData){
        GetAllProductResponseDto getAllProductResponseDto = new GetAllProductResponseDto();
        getAllProductResponseDto.setPid(productResponseData.getPid());
        getAllProductResponseDto.setName(productResponseData.getName());
        getAllProductResponseDto.setImages(toProductImageResponseDtoList(productResponseData.getProductImageList()));
        getAllProductResponseDto.setPrice(productResponseData.getPrice());
        getAllProductResponseDto.setHasStock(productResponseData.getStock()>0);
        getAllProductResponseDto.setCategories(productCategoryDtoMapper.toProductCategoryResponseDtoList(productResponseData.getProductCategoryResponseDataList()));
        getAllProductResponseDto.setIconVariable(productResponseData.getIconVariable());
        getAllProductResponseDto.setLinkedIds(productResponseData.getLinkedIds());
        getAllProductResponseDto.setVariantType(productResponseData.getVariantType());
        getAllProductResponseDto.setVariant(productResponseData.getVariant());
        return getAllProductResponseDto;
    }
    public List<GetAllProductResponseDto> toProductResponseDtoList(List<ProductResponseData> productResponseDataList){
        List<GetAllProductResponseDto> getAllProductResponseDtoList = new ArrayList<>();
        for (ProductResponseData productResponseData : productResponseDataList){
            GetAllProductResponseDto getAllProductResponseDto = getAllProductResponseDto(productResponseData);
            getAllProductResponseDtoList.add(getAllProductResponseDto);
        }
        return getAllProductResponseDtoList;
    }

    public ProductResponseDto toProductResponseDto(ProductResponseData productResponseData){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setPid(productResponseData.getPid());
        productResponseDto.setName(productResponseData.getName());
        productResponseDto.setDescription(productResponseData.getDescription());
        productResponseDto.setProductImageList(toProductImageResponseDtoList(productResponseData.getProductImageList()));
        productResponseDto.setPrice(productResponseData.getPrice());
        productResponseDto.setStock(productResponseData.getStock());
        productResponseDto.setCategory(productResponseData.getProductCategoryResponseDataList().getFirst().getCategory());
        productResponseDto.setIconVariable(productResponseData.getIconVariable());
        productResponseDto.setLinkedIds(productResponseData.getLinkedIds());
        productResponseDto.setVariantType(productResponseData.getVariantType());
        productResponseDto.setVariant(productResponseData.getVariant());

        return productResponseDto;
    }

}
