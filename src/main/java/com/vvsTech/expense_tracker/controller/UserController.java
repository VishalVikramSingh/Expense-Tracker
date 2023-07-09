package com.vvsTech.expense_tracker.controller;

import com.vvsTech.expense_tracker.model.User;
import com.vvsTech.expense_tracker.request.CreateUserRequest;
import com.vvsTech.expense_tracker.response.CreateUserResponse;
import com.vvsTech.expense_tracker.response.GenericResponse;
import com.vvsTech.expense_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/expenseTracker")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public GenericResponse<CreateUserResponse> addUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        CreateUserResponse createUserResponse = userService.addUser(createUserRequest);
        GenericResponse genericResponse = GenericResponse.builder()
                                                        .data(createUserResponse)
                                                        .code(HttpStatus.OK.value())
                                                        .message(createUserResponse.getMessage())
                                                        .statusCode(0) // 0 for success, 1 for failure
                                                        .build();
        return genericResponse;
    }

    @GetMapping("/getUser")
    public GenericResponse<User> getUser(@RequestParam String email){
        User user = userService.getUser(email);
        GenericResponse genericResponse = GenericResponse.builder()
                .message("here's the user")
                .code(0)
                .statusCode(HttpStatus.OK.value())
                .data(user)
                .build();
        return genericResponse;
    }

    @GetMapping("/testApiRedis")
    public GenericResponse<Map<String, Object>> testApiRedis(@RequestParam String name){
        Map<String, Object> map = userService.testApiRedis(name);
        GenericResponse genericResponse = GenericResponse.builder()
                .data(map)
                .statusCode(HttpStatus.OK.value())
                .code(0)
                .message("here's your hashmap")
                .build();
        return genericResponse;
    }
}
