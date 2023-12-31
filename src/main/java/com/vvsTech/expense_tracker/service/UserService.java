package com.vvsTech.expense_tracker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vvsTech.expense_tracker.model.User;
import com.vvsTech.expense_tracker.model.UserStatus;
import com.vvsTech.expense_tracker.repository.UserRepository;
import com.vvsTech.expense_tracker.request.CreateUserRequest;
import com.vvsTech.expense_tracker.response.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public CreateUserResponse addUser(CreateUserRequest createUserRequest){

        User userFromDB = userRepository.findByEmailAddressJPQL(createUserRequest.getEmailId());
        CreateUserResponse createUserResponse;
        if(userFromDB==null){
            User user = createUserRequest.toUser();
            user.setUserStatus(UserStatus.ACTIVE);
            userFromDB = userRepository.save(user);
            storeToRedis(userFromDB);
            createUserResponse = CreateUserResponse.builder().message("User Added").userId(userFromDB.getId()).build();
        }
        else{
            createUserResponse = CreateUserResponse.builder().message("User Already present").userId(userFromDB.getId()).build();
        }

        return createUserResponse;
    }

    public User getUser(String email){
        User userFromCache = (User) redisTemplate.opsForValue().get(email);
        if(userFromCache!=null){
//            System.out.print("USED THE CACHE!!!");
            return userFromCache;
        }
        else{
            User userFromDB = userRepository.findByEmail(email);
            storeToRedis(userFromDB);
            return userFromDB;
        }
    }

    private void storeToRedis(User user){
        redisTemplate.opsForValue().set(user.getEmail(),user);
        redisTemplate.opsForHash().putAll(user.getName(), objectMapper.convertValue(user, Map.class));
    }

    public Map<String, Object> testApiRedis(String name) {
        return redisTemplate.opsForHash().entries(name);
    }
}
