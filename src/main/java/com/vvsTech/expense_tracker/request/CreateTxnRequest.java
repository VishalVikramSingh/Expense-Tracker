package com.vvsTech.expense_tracker.request;

import com.vvsTech.expense_tracker.model.TxnDetails;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTxnRequest {

    @NotBlank(message = "userEmail cannot be blank")
    private String userEmail;

    private LocalDate expenseDate; //

    private String expenseNote; //

    @NotNull(message = "expenditure amount cannot be null")
    private Double expenditureAmount; //

    @NotBlank(message = "expense type cannot be blank")
    private String expenseType; //

    public TxnDetails toTxnDetails(){
        return TxnDetails.builder()
                .expenditureAmount(this.expenditureAmount)
                .expenseDate(this.expenseDate)
                .expenseNote(this.expenseNote)
                .build();
    }
}
