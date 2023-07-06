package com.vvsTech.expense_tracker.request;

import com.vvsTech.expense_tracker.model.ExpenseTypes;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateExpenseTypeRequest {

    @NotBlank(message = "expense type cannot be blank")
    String expenseType;
    @NotBlank(message = "creator's email cannot be blank")
    String userEmail;

    public ExpenseTypes toExpenseTypes(){
        return ExpenseTypes.builder()
                .createdBy(this.userEmail)
                .expenseType(this.expenseType)
                .build();
    }

}
