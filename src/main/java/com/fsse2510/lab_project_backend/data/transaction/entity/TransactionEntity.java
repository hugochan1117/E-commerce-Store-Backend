package com.fsse2510.lab_project_backend.data.transaction.entity;

import com.fsse2510.lab_project_backend.data.transaction_product.entity.TransactionProductEntity;
import com.fsse2510.lab_project_backend.data.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private BigDecimal total;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = false)
    private List<TransactionProductEntity> transactionProductEntityList = new ArrayList<>();

    @Column
    private String sessionId;
}
