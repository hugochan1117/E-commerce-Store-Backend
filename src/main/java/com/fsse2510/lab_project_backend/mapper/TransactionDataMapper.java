package com.fsse2510.lab_project_backend.mapper;

import com.fsse2510.lab_project_backend.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2510.lab_project_backend.data.transaction.entity.TransactionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionDataMapper {
    private final TransactionProductDataMapper transactionProductDataMapper;

    public TransactionDataMapper(TransactionProductDataMapper transactionProductDataMapper) {
        this.transactionProductDataMapper = transactionProductDataMapper;
    }

    public TransactionResponseData toTransactionResponseData(TransactionEntity transactionEntity){
        TransactionResponseData transactionResponseData = new TransactionResponseData();
        transactionResponseData.setTid(transactionEntity.getTid());
        transactionResponseData.setStatus(transactionEntity.getStatus());
        transactionResponseData.setTotal(transactionEntity.getTotal());
        transactionResponseData.setDateTime(transactionEntity.getDateTime());
        transactionResponseData.setUser(transactionEntity.getUser());
        transactionResponseData.setTransactionProductResponseDataList(transactionProductDataMapper.toTransactionProductResponseDataList(transactionEntity.getTransactionProductEntityList()));
        return transactionResponseData;
    }

    public List<TransactionResponseData> toTransactionResponseDataList(List<TransactionEntity> transactionEntitiyList){
        List<TransactionResponseData> transactionResponseDataList = new ArrayList<>();
        for (TransactionEntity transactionEntity: transactionEntitiyList){
            transactionResponseDataList.add(toTransactionResponseData(transactionEntity));
        }
        return transactionResponseDataList;
    }

}
