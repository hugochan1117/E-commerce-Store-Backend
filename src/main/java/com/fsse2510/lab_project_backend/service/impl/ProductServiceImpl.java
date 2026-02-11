package com.fsse2510.lab_project_backend.service.impl;

import com.fsse2510.lab_project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2510.lab_project_backend.data.product.entity.ProductEntity;
import com.fsse2510.lab_project_backend.exception.CategoryNotFound;
import com.fsse2510.lab_project_backend.exception.ProductNotFoundException;
import com.fsse2510.lab_project_backend.mapper.ProductDataMapper;
import com.fsse2510.lab_project_backend.repository.ProductRepository;
import com.fsse2510.lab_project_backend.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDataMapper productDataMapper;
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository productRepository, ProductDataMapper productDataMapper) {
        this.productRepository = productRepository;
        this.productDataMapper = productDataMapper;
    }

    public List<ProductResponseData> getAllProduct(){
        Iterable<ProductEntity> productEntityIterable = productRepository.findAll();
        return productDataMapper.toProductResponseDataList(productEntityIterable);
    }

    public ProductResponseData getProductResponseDataByPid(Integer pid){
        try{
            Optional<ProductEntity> productEntity = productRepository.findByPid(pid);
            if (productEntity.isEmpty()){
                throw new ProductNotFoundException(pid);
            }
            return productDataMapper.toProductResponseData(productEntity.get());

        } catch (Exception e){
            logger.warn("Get Product by pid failed: {}", e.getMessage());
            throw e;

        }
    }
    public List<ProductResponseData> getProductResponseDataByCategory(String category){
        try{
            List<ProductEntity> productEntityList = productRepository.findAllByCategories_Category(category);
            if (productEntityList.isEmpty()){
                throw new CategoryNotFound(category);
            }
            return productDataMapper.toProductResponseDataList(productEntityList);
        } catch (Exception e){
            logger.warn("Get Products by category failed: {}", e.getMessage());
            throw e;
        }
    }


    public ProductEntity getProductEntityByPid(Integer pid){
        try{
            Optional<ProductEntity> productEntity = productRepository.findByPid(pid);
            if (productEntity.isEmpty()){
                throw new ProductNotFoundException(pid);
            }
            return productEntity.get();

        } catch (Exception e){
            logger.warn("Get Product by pid failed: {}", e.getMessage());
            throw e;

        }
    }

    public List<ProductResponseData> getSearchResponseData(String search){
            List<ProductEntity> productEntityList = productRepository.findAllByNameContainingIgnoreCase(search);
            return productDataMapper.toProductResponseDataList(productEntityList);
    }


}
