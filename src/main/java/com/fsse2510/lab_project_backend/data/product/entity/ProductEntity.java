package com.fsse2510.lab_project_backend.data.product.entity;

import com.fsse2510.lab_project_backend.data.product_category.entity.ProductCategoryEntity;
import com.fsse2510.lab_project_backend.data.productImage.entity.ProductImageEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;


@Entity
@Table(name = "product")
@Getter @Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImageEntity> images = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCategoryEntity> categories = new ArrayList<>();

    @Column
    private String variantType = "";

    @Type(JsonType.class)
    @Column(columnDefinition = "json", nullable = false)
    private List<Integer> linkedIds = List.of();


    @Type(JsonType.class)
    @Column(columnDefinition = "json", nullable = false)
    private List<String> iconVariable = List.of();

    @Type(JsonType.class)
    @Column(columnDefinition = "json", nullable = false)
    private List<String> variant = List.of();
}
