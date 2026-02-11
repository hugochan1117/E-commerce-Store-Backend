package com.fsse2510.lab_project_backend.data.transaction_product.domainObject.response;

import com.fsse2510.lab_project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2510.lab_project_backend.data.transaction.entity.TransactionEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class TransactionProductResponseData {
    private TransactionEntity transaction;
    private ProductResponseData product;
    private Integer quantity;
    private BigDecimal subtotal;
}
