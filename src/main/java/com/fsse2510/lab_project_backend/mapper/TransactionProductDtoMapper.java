package com.fsse2510.lab_project_backend.mapper;

import com.fsse2510.lab_project_backend.data.transaction_product.domainObject.response.TransactionProductResponseData;
import com.fsse2510.lab_project_backend.data.transaction_product.dto.response.TransactionProductResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionProductDtoMapper {
    private final ProductDtoMapper productDtoMapper;

    public TransactionProductDtoMapper(ProductDtoMapper productDtoMapper) {
        this.productDtoMapper = productDtoMapper;
    }

    public TransactionProductResponseDto toTransactionProductResponseDto(TransactionProductResponseData transactionProductResponseData){
        TransactionProductResponseDto transactionProductResponseDto = new TransactionProductResponseDto();
        transactionProductResponseDto.setProduct(productDtoMapper.toProductResponseDto(transactionProductResponseData.getProduct()));
        transactionProductResponseDto.setTid(transactionProductResponseData.getTransaction().getTid());
        transactionProductResponseDto.setSubtotal(transactionProductResponseData.getSubtotal());
        transactionProductResponseDto.setQuantity(transactionProductResponseData.getQuantity());
        return transactionProductResponseDto;
    }

    public List<TransactionProductResponseDto> toTransactionProductResponseDtoList(List<TransactionProductResponseData> transactionProductResponseDataList){
        List<TransactionProductResponseDto> transactionProductResponseDtoList = new ArrayList<>();
        for (TransactionProductResponseData transactionProductResponseData : transactionProductResponseDataList){
            transactionProductResponseDtoList.add(toTransactionProductResponseDto(transactionProductResponseData));
        }
        return transactionProductResponseDtoList;
    }
}
