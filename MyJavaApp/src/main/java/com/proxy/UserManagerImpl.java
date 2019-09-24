package com.proxy;

/**
 * Created by ddf on 16/6/8.
 *
 * 被代理类
 */
public class UserManagerImpl implements IUserManager {

    public void addUser(String userId, String userName) {
        System.out.println("被代理类输出: AddUser");
    }
}
