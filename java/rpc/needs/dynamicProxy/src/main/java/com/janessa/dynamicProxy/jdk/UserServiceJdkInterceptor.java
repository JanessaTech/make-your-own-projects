package com.janessa.dynamicProxy.jdk;

import com.janessa.dynamicProxy.common.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserServiceJdkInterceptor implements InvocationHandler {
    private Object realObj;

    public UserServiceJdkInterceptor(Object realObject) {
        super();
        this.realObj = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if (args != null && args.length > 0 && args[0] instanceof User) {
            User user = (User)args[0];
            System.out.println("we are in UserServiceJdkInterceptor. " + user.getName() + " will be added");
            result = method.invoke(realObj, args);
        }
        return result;
    }
}
