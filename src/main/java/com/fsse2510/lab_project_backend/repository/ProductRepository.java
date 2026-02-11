package com.fsse2510.lab_project_backend.repository;

import com.fsse2510.lab_project_backend.data.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findByPid(Integer pid);

    List<ProductEntity> findAllByCategories_Category(String category);

    List<ProductEntity> findAllByNameContainingIgnoreCase(String search);

    @Modifying
    @Query("UPDATE ProductEntity p SET p.stock = :stock WHERE p.pid = :pid")
    void updateStock(@Param("pid") Integer pid, @Param("stock") int stock);
}
