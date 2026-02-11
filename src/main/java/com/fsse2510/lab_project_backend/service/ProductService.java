package com.fsse2510.lab_project_backend.service;

import com.fsse2510.lab_project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2510.lab_project_backend.data.product.entity.ProductEntity;

import java.util.List;


public interface ProductService {
    List<ProductResponseData> getAllProduct();
    ProductResponseData getProductResponseDataByPid(Integer pid);
    ProductEntity getProductEntityByPid(Integer pid);
    List<ProductResponseData> getProductResponseDataByCategory(String category);
    List<ProductResponseData> getSearchResponseData(String search);
}
