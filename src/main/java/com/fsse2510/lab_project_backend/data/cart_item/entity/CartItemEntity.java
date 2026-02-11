package com.fsse2510.lab_project_backend.data.cart_item.entity;

import com.fsse2510.lab_project_backend.data.product.entity.ProductEntity;
import com.fsse2510.lab_project_backend.data.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class CartItemEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "pid", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private Integer quantity;


}
