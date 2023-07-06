package com.vvsTech.expense_tracker.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateExpenseTypeResponse {

    private Integer userId;

    private String message;

    private String expenseType;
}
