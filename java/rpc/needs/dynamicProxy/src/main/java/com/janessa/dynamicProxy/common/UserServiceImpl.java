package com.janessa.dynamicProxy.common;

public class UserServiceImpl implements UserService {
    @Override
    public void addUser(User user) {
        System.out.println(user.getName() + " is added successfully");
    }
}
