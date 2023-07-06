package com.vvsTech.expense_tracker.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTxnResponse {

    private String message;

    private Integer userId;

    private Integer txnId;

}
