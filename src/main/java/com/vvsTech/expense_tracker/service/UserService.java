package com.vvsTech.expense_tracker.service;

import com.vvsTech.expense_tracker.model.User;
import com.vvsTech.expense_tracker.model.UserStatus;
import com.vvsTech.expense_tracker.repository.UserRepository;
import com.vvsTech.expense_tracker.request.CreateUserRequest;
import com.vvsTech.expense_tracker.response.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public CreateUserResponse addUser(CreateUserRequest createUserRequest){

        User userFromDB = userRepository.findByEmailAddressJPQL(createUserRequest.getEmailId());
        CreateUserResponse createUserResponse;
        if(userFromDB==null){
            User user = createUserRequest.toUser();
            user.setUserStatus(UserStatus.ACTIVE);
            userFromDB = userRepository.save(user);
            createUserResponse = CreateUserResponse.builder().message("User Added").userId(userFromDB.getId()).build();
        }
        else{
            createUserResponse = CreateUserResponse.builder().message("User Already present").userId(userFromDB.getId()).build();
        }

        return createUserResponse;
    }

}
