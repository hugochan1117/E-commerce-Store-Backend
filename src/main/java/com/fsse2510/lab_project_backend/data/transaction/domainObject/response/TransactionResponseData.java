package com.fsse2510.lab_project_backend.data.transaction.domainObject.response;

import com.fsse2510.lab_project_backend.data.transaction_product.domainObject.response.TransactionProductResponseData;
import com.fsse2510.lab_project_backend.data.user.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class TransactionResponseData {
    private Integer tid;
    private UserEntity user;
    private LocalDateTime dateTime;
    private String status;
    private BigDecimal total;
    private List<TransactionProductResponseData> transactionProductResponseDataList;
}
