package com.vvsTech.expense_tracker.repository;

import com.vvsTech.expense_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select u from user u where user.email =:email", nativeQuery = true) // query which runs on sql
    public User findByEmailAddress(String email);
    @Query(value = "select u from User u where u.email =:email") // runs on hibernate uses class name model names
    public User findByEmailAddressJPQL(String email);

    public User findByEmail(String email);

}
