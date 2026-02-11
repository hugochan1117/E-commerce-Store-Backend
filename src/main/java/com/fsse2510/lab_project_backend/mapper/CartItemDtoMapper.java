package com.fsse2510.lab_project_backend.mapper;

import com.fsse2510.lab_project_backend.data.cart_item.domainObject.response.CartItemResponseData;
import com.fsse2510.lab_project_backend.data.cart_item.dto.response.CartItemResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemDtoMapper {
    private final ProductDtoMapper productDtoMapper;

    public CartItemDtoMapper(ProductDtoMapper productDtoMapper) {
        this.productDtoMapper = productDtoMapper;
    }

    public CartItemResponseDto toCartItemResponseDto(CartItemResponseData cartItemResponseData){
        CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();
        cartItemResponseDto.setPid(cartItemResponseData.getProduct().getPid());
        cartItemResponseDto.setName(cartItemResponseData.getProduct().getName());
        cartItemResponseDto.setImages(productDtoMapper.toProductImageResponseDtoList(cartItemResponseData.getProduct().getProductImageList()));
        cartItemResponseDto.setPrice(cartItemResponseData.getProduct().getPrice());
        cartItemResponseDto.setCartQuantity(cartItemResponseData.getQuantity());
        return cartItemResponseDto;
    }

    public List<CartItemResponseDto> toCartItemResponseDtoList(List<CartItemResponseData> cartItemResponseDataList){
        List<CartItemResponseDto> cartItemResponseDtoList = new ArrayList<>();
        for (CartItemResponseData cartItemResponseData : cartItemResponseDataList){
            cartItemResponseDtoList.add(toCartItemResponseDto(cartItemResponseData));
        }
        return cartItemResponseDtoList;
    }
}
