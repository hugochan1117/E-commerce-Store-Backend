package com.fsse2510.lab_project_backend.mapper;

import com.fsse2510.lab_project_backend.data.cart_item.domainObject.response.CartItemResponseData;
import com.fsse2510.lab_project_backend.data.cart_item.entity.CartItemEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemDataMapper {

    private final ProductDataMapper productDataMapper;

    public CartItemDataMapper(ProductDataMapper productDataMapper) {
        this.productDataMapper = productDataMapper;
    }

    public CartItemResponseData toCartItemResponseData(CartItemEntity cartItemEntity){
        CartItemResponseData cartItemResponseData = new CartItemResponseData();
        cartItemResponseData.setCid(cartItemEntity.getCid());
        cartItemResponseData.setUser(cartItemEntity.getUser());
        cartItemResponseData.setProduct(productDataMapper.toProductResponseData(cartItemEntity.getProduct()));
        cartItemResponseData.setQuantity(cartItemEntity.getQuantity());
        return cartItemResponseData;
    }

    public List<CartItemResponseData> toCartItemResponseDataList(List<CartItemEntity> cartItemEntityList){
        List<CartItemResponseData> cartItemResponseDataList = new ArrayList<>();
        for (CartItemEntity cartItemEntity : cartItemEntityList){
            cartItemResponseDataList.add(toCartItemResponseData(cartItemEntity));
        }
        return cartItemResponseDataList;
    }
}
