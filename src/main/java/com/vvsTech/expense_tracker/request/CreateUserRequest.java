package com.vvsTech.expense_tracker.request;

import com.vvsTech.expense_tracker.model.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {

    String name;

    @NotBlank(message = "user email id must not be blank")
    String emailId;

    @NotNull(message = "user contact cannot be null")
    String contact;

    public User toUser(){
        return User.builder().name(this.name).
                              email(this.emailId).
                              contact(this.contact).build();

    }
}
