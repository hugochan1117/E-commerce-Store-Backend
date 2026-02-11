package com.fsse2510.lab_project_backend.repository;

import com.fsse2510.lab_project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2510.lab_project_backend.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    Optional<TransactionEntity> findByTid(Integer Tid);
    List<TransactionEntity> findAllByUserAndStatus(UserEntity user, String status);
}
