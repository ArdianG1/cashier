package com.smk.cashier.service;

import com.smk.cashier.model.User;
import org.junit.jupiter.api.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @org.junit.jupiter.api.Test
    @Order(1)
    void getUserList() {
        List<User> userList = UserService.getInstance().getUserList();
        assertEquals(userList.size(),1);
    }

    @org.junit.jupiter.api.Test
    @Order(2)
    void addUser() {
        User user = new User();
        user.setUserName("Bina Informatika");
        user.setPassword("HOHOHO");
        UserService.getInstance().addUser(user);
    }
}