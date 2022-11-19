package com.janessa.dynamicProxy.jdk;

import com.janessa.dynamicProxy.common.User;
import com.janessa.dynamicProxy.common.UserService;
import com.janessa.dynamicProxy.common.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ClientTest {
    public static void main(String[] args) {
        UserService target = new UserServiceImpl(); // The real object
        InvocationHandler interceptor = new UserServiceJdkInterceptor(target);
        UserService proxy = (UserService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), interceptor);

        User user = new User();
        user.setName("JanessaTech2022");
        proxy.addUser(user);
    }
}
