package com.fsse2510.lab_project_backend.repository;

import com.fsse2510.lab_project_backend.data.cart_item.entity.CartItemEntity;
import com.fsse2510.lab_project_backend.data.product.entity.ProductEntity;
import com.fsse2510.lab_project_backend.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity,Integer>{
    List<CartItemEntity> findAllByUser(UserEntity user);
    Optional<CartItemEntity> findByUserAndProduct(UserEntity user, ProductEntity product);
    void deleteAllByUser(UserEntity user);

}
