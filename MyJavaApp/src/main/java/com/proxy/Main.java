package com.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by ddf on 16/6/8.
 *
 * 演示动态代理
 */
public class Main {
    public static void main(String[] args) {
        UserManagerImpl userManagerImpl = new UserManagerImpl();
        LogHandler logHandler = new LogHandler(userManagerImpl);
        IUserManager userManager = (IUserManager) Proxy.newProxyInstance(userManagerImpl.getClass().getClassLoader(), userManagerImpl.getClass().getInterfaces(), logHandler);
        userManager.addUser("0001", "张三");
    }
}
