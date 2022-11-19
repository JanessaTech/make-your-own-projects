package com.janessa.dynamicProxy.cglib;

import com.janessa.dynamicProxy.common.User;
import com.janessa.dynamicProxy.common.UserService;
import com.janessa.dynamicProxy.common.UserServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class ClientTest {
    public static void main(String[] args) {
        UserService target = new UserServiceImpl();
        MethodInterceptor interceptor = new UserServiceCglibInterceptor(target);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(interceptor);

        User user = new User();
        user.setName("JanessaTech2022");
        UserService proxy = (UserService) enhancer.create();
        proxy.addUser(user);

    }
}
