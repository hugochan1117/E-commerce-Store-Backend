package com.fsse2510.lab_project_backend.controller;

import com.fsse2510.lab_project_backend.config.EnvConfig;
import com.fsse2510.lab_project_backend.data.Stripe.dto.response.StripeResponseDto;
import com.fsse2510.lab_project_backend.data.transaction.dto.response.TransactionResponseDto;
import com.fsse2510.lab_project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2510.lab_project_backend.mapper.StripeDtoMapper;
import com.fsse2510.lab_project_backend.mapper.TransactionDtoMapper;
import com.fsse2510.lab_project_backend.mapper.UserDataMapper;
import com.fsse2510.lab_project_backend.service.TransactionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin({EnvConfig.DEV_BASE_URL, EnvConfig.PROD_BASE_URL})
public class TransactionController {
    private final UserDataMapper userDataMapper;
    private final TransactionService transactionService;
    private final TransactionDtoMapper transactionDtoMapper;
    private final StripeDtoMapper stripeDtoMapper;

    public TransactionController(UserDataMapper userDataMapper, TransactionService transactionService, TransactionDtoMapper transactionDtoMapper, StripeDtoMapper stripeDtoMapper) {
        this.userDataMapper = userDataMapper;
        this.transactionService = transactionService;
        this.transactionDtoMapper = transactionDtoMapper;
        this.stripeDtoMapper = stripeDtoMapper;
    }

    @PostMapping
    public TransactionResponseDto createTransaction(@AuthenticationPrincipal Jwt jwt){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        return transactionDtoMapper.toTransactionResponseDto(transactionService.createTransaction(firebaseUserData));
    }

    @GetMapping("/{tid}")
    public TransactionResponseDto getTransactionByTid(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        return transactionDtoMapper.toTransactionResponseDto(transactionService.getTransactionResponseDataByTid(firebaseUserData, tid));
    }

    @PatchMapping("/{tid}/payment")
    public StripeResponseDto updateStatusToProcessing(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid) throws Exception {
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        return stripeDtoMapper.toStripeResponseDto(transactionService.updateTransactionStatusToProcessing(firebaseUserData, tid));
    }

    @PatchMapping("/{tid}/success")
    public TransactionResponseDto updateStatusToSuccess(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid) throws Exception {
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        return transactionDtoMapper.toTransactionResponseDto(transactionService.updateTransactionStatusToSuccess(firebaseUserData, tid));
    }
    @GetMapping("/OrderHistory")
    public List<TransactionResponseDto> getOrderHistory(@AuthenticationPrincipal Jwt jwt){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        return transactionDtoMapper.toTransactionResponseDtoList(transactionService.getOrderHistory(firebaseUserData));
    }


}