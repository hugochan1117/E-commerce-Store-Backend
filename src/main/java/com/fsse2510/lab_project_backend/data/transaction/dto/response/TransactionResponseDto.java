package com.fsse2510.lab_project_backend.data.transaction.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fsse2510.lab_project_backend.data.transaction_product.dto.response.TransactionProductResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TransactionResponseDto {
    private Integer tid;
    private Integer buyerUid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    private String status;
    private BigDecimal total;
    private List<TransactionProductResponseDto> transactionProductResponseDtoList;
}
