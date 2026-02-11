package com.fsse2510.lab_project_backend.controller;

import com.fsse2510.lab_project_backend.config.EnvConfig;
import com.fsse2510.lab_project_backend.data.product.dto.response.GetAllProductResponseDto;
import com.fsse2510.lab_project_backend.data.product.dto.response.ProductResponseDto;
import com.fsse2510.lab_project_backend.mapper.ProductDtoMapper;
import com.fsse2510.lab_project_backend.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin({EnvConfig.DEV_BASE_URL, EnvConfig.PROD_BASE_URL})
public class ProductController {
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    public ProductController(ProductService productService, ProductDtoMapper productDtoMapper) {
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
    }

    @GetMapping("/public/products")
    public List<GetAllProductResponseDto> getAllProduct(){
        return productDtoMapper.toProductResponseDtoList(productService.getAllProduct());
    }

    @GetMapping("/public/products/{pid}")
    public ProductResponseDto getProductByPid(@PathVariable Integer pid){
        return productDtoMapper.toProductResponseDto(productService.getProductResponseDataByPid(pid));
    }
    @GetMapping("/public/products/category/{category}")
    public List<GetAllProductResponseDto> getProductsByCategory(@PathVariable String category){
        return productDtoMapper.toProductResponseDtoList(productService.getProductResponseDataByCategory(category));
    }

    @GetMapping("/public/products/search/{search}")
    public List<GetAllProductResponseDto> searchProducts(@PathVariable String search){
        return productDtoMapper.toProductResponseDtoList(productService.getSearchResponseData(search));
    }


}
