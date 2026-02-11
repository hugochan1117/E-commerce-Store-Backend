package com.fsse2510.lab_project_backend.data.cart_item.domainObject.response;

import com.fsse2510.lab_project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2510.lab_project_backend.data.user.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartItemResponseData {
    private Integer cid;

    private UserEntity user;

    private ProductResponseData product;

    private Integer quantity;


}
