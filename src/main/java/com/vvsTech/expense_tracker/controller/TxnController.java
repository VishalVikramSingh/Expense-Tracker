package com.vvsTech.expense_tracker.controller;

import com.vvsTech.expense_tracker.model.TxnDetails;
import com.vvsTech.expense_tracker.repository.TxnRepository;
import com.vvsTech.expense_tracker.request.CreateTxnRequest;
import com.vvsTech.expense_tracker.response.CreateTxnResponse;
import com.vvsTech.expense_tracker.response.GenericResponse;
import com.vvsTech.expense_tracker.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/expenseTracker")
public class TxnController {

    @Autowired
    TxnService txnService;

    @Autowired
    TxnRepository txnRepository;

    @PostMapping("/addTxn")
    public GenericResponse addTxn(@Valid @RequestBody CreateTxnRequest createTxnRequest){
        CreateTxnResponse createTxnResponse = txnService.addTxn(createTxnRequest);
        GenericResponse genericResponse = GenericResponse.builder()
                .message(createTxnResponse.getMessage())
                .code(0)
                .statusCode(HttpStatus.OK.value())
                .data(createTxnResponse)
                .build();
        return genericResponse;
    }

    @PostMapping("/fetchTxn")
    public GenericResponse fetchTxn(@RequestParam("expense_type") String expenseType){
        List<TxnDetails> list = txnRepository.findByExpenseTypes_expenseType(expenseType);
        GenericResponse genericResponse = GenericResponse.builder()
                .data(list)
                .statusCode(HttpStatus.OK.value())
                .code(0)
                .message("here are the txns")
                .build();
        return genericResponse;
    }
}













