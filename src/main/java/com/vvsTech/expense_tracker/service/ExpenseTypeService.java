package com.vvsTech.expense_tracker.service;

import com.vvsTech.expense_tracker.model.ExpenseTypes;
import com.vvsTech.expense_tracker.model.User;
import com.vvsTech.expense_tracker.repository.ExpenseTypesRepository;
import com.vvsTech.expense_tracker.repository.UserRepository;
import com.vvsTech.expense_tracker.request.CreateExpenseTypeRequest;
import com.vvsTech.expense_tracker.response.CreateExpenseTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseTypeService {

    @Autowired
    ExpenseTypesRepository expenseTypesRepository;
    @Autowired
    UserRepository userRepository;
    public CreateExpenseTypeResponse addExpenseType(CreateExpenseTypeRequest createExpenseTypeRequest) {

        User userFromDb = userRepository.findByEmail(createExpenseTypeRequest.getUserEmail());
        if(userFromDb==null){
            return CreateExpenseTypeResponse.builder()
                    .message("first create a user with this email id").build();
        }

        ExpenseTypes expenseTypeFromDB = expenseTypesRepository.findByExpenseType(createExpenseTypeRequest.getExpenseType());
        if(expenseTypeFromDB!=null){
            return CreateExpenseTypeResponse.builder()
                    .message("this type of expense type already exists and it was made by...")
                    .userId(expenseTypeFromDB.getId())
                    .build();
        }

        ExpenseTypes expenseTypes = createExpenseTypeRequest.toExpenseTypes();
        ExpenseTypes e = expenseTypesRepository.save(expenseTypes);
        return CreateExpenseTypeResponse.builder()
                .message("expense type added")
                .userId(e.getId())
                .expenseType(e.getExpenseType())
                .build();

    }
}