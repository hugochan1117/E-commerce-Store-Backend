package com.fsse2510.lab_project_backend.mapper;

import com.fsse2510.lab_project_backend.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2510.lab_project_backend.data.transaction.dto.response.TransactionResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionDtoMapper {
    private final TransactionProductDtoMapper transactionProductDtoMapper;

    public TransactionDtoMapper(TransactionProductDtoMapper transactionProductDtoMapper) {
        this.transactionProductDtoMapper = transactionProductDtoMapper;
    }

    public TransactionResponseDto toTransactionResponseDto(TransactionResponseData transactionResponseData){
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setTid(transactionResponseData.getTid());
        transactionResponseDto.setTransactionProductResponseDtoList(transactionProductDtoMapper.toTransactionProductResponseDtoList(transactionResponseData.getTransactionProductResponseDataList()));
        transactionResponseDto.setTotal(transactionResponseData.getTotal());
        transactionResponseDto.setBuyerUid(transactionResponseData.getUser().getUid());
        transactionResponseDto.setStatus(transactionResponseData.getStatus());
        transactionResponseDto.setDateTime(transactionResponseData.getDateTime());

        return transactionResponseDto;
    }
    public List<TransactionResponseDto> toTransactionResponseDtoList(List<TransactionResponseData> transactionResponseDataList){
        List<TransactionResponseDto> transactionResponseDtos = new ArrayList<>();
        for (TransactionResponseData transactionResponseData: transactionResponseDataList){
            transactionResponseDtos.add(toTransactionResponseDto(transactionResponseData));
        }
        return transactionResponseDtos;
    }
}
