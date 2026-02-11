package com.fsse2510.lab_project_backend.service;

import com.fsse2510.lab_project_backend.data.cart_item.domainObject.response.CartItemResponseData;
import com.fsse2510.lab_project_backend.data.cart_item.entity.CartItemEntity;
import com.fsse2510.lab_project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2510.lab_project_backend.data.user.entity.UserEntity;

import java.util.List;


public interface CartItemService{
    void putItemInCart(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);
    List<CartItemResponseData> getUserCart(FirebaseUserData firebaseUserData);
    void updateCartQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);
    void deleteCartProduct(FirebaseUserData firebaseUserData, Integer pid);
    List<CartItemEntity> getCartItemListByUser(UserEntity user);
    void deleteAllItems(UserEntity user);
}
