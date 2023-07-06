package com.vvsTech.expense_tracker.controller;

import com.vvsTech.expense_tracker.request.CreateExpenseTypeRequest;
import com.vvsTech.expense_tracker.response.CreateExpenseTypeResponse;
import com.vvsTech.expense_tracker.response.GenericResponse;
import com.vvsTech.expense_tracker.service.ExpenseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/expenseTracker")
public class ExpenseTypeController {

    @Autowired
    ExpenseTypeService expenseTypeService;

    @PostMapping("/addExpenseType")
    public GenericResponse<CreateExpenseTypeResponse> addExpenseType(@Valid @RequestBody CreateExpenseTypeRequest createExpenseTypeRequest){
        CreateExpenseTypeResponse createExpenseTypeResponse = expenseTypeService.addExpenseType(createExpenseTypeRequest);
        GenericResponse genericResponse = GenericResponse.builder()
                .data(createExpenseTypeResponse)
                .code(0)
                .statusCode(HttpStatus.OK.value())
                .message(createExpenseTypeResponse.getMessage())
                .build();
        return genericResponse;
    }

}
