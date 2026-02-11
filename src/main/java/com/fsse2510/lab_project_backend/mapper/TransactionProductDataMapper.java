package com.fsse2510.lab_project_backend.mapper;

import com.fsse2510.lab_project_backend.data.transaction_product.domainObject.response.TransactionProductResponseData;
import com.fsse2510.lab_project_backend.data.transaction_product.entity.TransactionProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionProductDataMapper {
    private final ProductDataMapper productDataMapper;

    public TransactionProductDataMapper(ProductDataMapper productDataMapper) {
        this.productDataMapper = productDataMapper;
    }

    public TransactionProductResponseData toTransactionProductResponseData(TransactionProductEntity transactionProductEntity){
        TransactionProductResponseData transactionProductResponseData = new TransactionProductResponseData();
        transactionProductResponseData.setProduct(productDataMapper.toProductResponseData(transactionProductEntity.getProduct()));
        transactionProductResponseData.setTransaction(transactionProductEntity.getTransaction());
        transactionProductResponseData.setSubtotal(transactionProductEntity.getSubtotal());
        transactionProductResponseData.setQuantity(transactionProductEntity.getQuantity());
        return transactionProductResponseData;
    }

    public List<TransactionProductResponseData> toTransactionProductResponseDataList(List<TransactionProductEntity> transactionProductEntities){
        List<TransactionProductResponseData> transactionProductResponseDataList = new ArrayList<>();
        for (TransactionProductEntity transactionProductEntity : transactionProductEntities){
            transactionProductResponseDataList.add(toTransactionProductResponseData(transactionProductEntity));
        }
        return transactionProductResponseDataList;
    }
}
