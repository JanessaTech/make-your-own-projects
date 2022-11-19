package com.janessa.dynamicProxy.cglib;

import com.janessa.dynamicProxy.common.User;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class UserServiceCglibInterceptor implements MethodInterceptor {
    private Object realObj;

    public UserServiceCglibInterceptor(Object realObject) {
        super();
        this.realObj = realObject;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result = null;
        if (args != null && args.length > 0 && args[0] instanceof User) {
            User user = (User)args[0];
            System.out.println("we are in UserServiceCglibInterceptor. " + user.getName() + " will be added");
            result = method.invoke(realObj, args);
        }
        return result;
    }
}
