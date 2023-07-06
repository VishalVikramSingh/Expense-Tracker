package com.vvsTech.expense_tracker.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse<T> {

    private Integer statusCode;
    private String message;
    private Integer code;
    private T data;

}
