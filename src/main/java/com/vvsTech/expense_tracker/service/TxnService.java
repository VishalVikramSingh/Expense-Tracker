package com.vvsTech.expense_tracker.service;

import com.vvsTech.expense_tracker.model.ExpenseTypes;
import com.vvsTech.expense_tracker.model.TxnDetails;
import com.vvsTech.expense_tracker.model.User;
import com.vvsTech.expense_tracker.repository.ExpenseTypesRepository;
import com.vvsTech.expense_tracker.repository.TxnRepository;
import com.vvsTech.expense_tracker.repository.UserRepository;
import com.vvsTech.expense_tracker.request.CreateTxnRequest;
import com.vvsTech.expense_tracker.response.CreateTxnResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TxnService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ExpenseTypesRepository expenseTypesRepository;
    @Autowired
    TxnRepository txnRepository;

    public CreateTxnResponse addTxn(CreateTxnRequest createTxnRequest){
        User userFromDB = userRepository.findByEmail(createTxnRequest.getUserEmail());
        if(userFromDB==null){
            return CreateTxnResponse.builder()
                    .message("no account for this email id exists")
                    .build();
        }
        ExpenseTypes expenseTypes = expenseTypesRepository.findByExpenseType(createTxnRequest.getExpenseType());
        if(expenseTypes==null){
            return CreateTxnResponse.builder()
                    .message("no expense type named "+"'"+createTxnRequest.getExpenseType()+"'"+" exists, first create one")
                    .build();
        }
        TxnDetails txnDetailsToBePushed = createTxnRequest.toTxnDetails();
        txnDetailsToBePushed.setExpenseTypes(expenseTypes);
        txnDetailsToBePushed.setUser(userFromDB);
        TxnDetails txnDetailsPushed = txnRepository.save(txnDetailsToBePushed);
        return CreateTxnResponse.builder()
                .message("txn added successfully")
                .userId(txnDetailsPushed.getUser().getId())
                .txnId(txnDetailsPushed.getId())
                .build();
    }

}
