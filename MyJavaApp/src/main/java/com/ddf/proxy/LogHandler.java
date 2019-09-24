package com.ddf.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by ddf on 16/6/8.
 *
 * 代理类
 */

public class LogHandler implements InvocationHandler {

    private Object target;

    public LogHandler(Object targetObject) {
        this.target = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("代理类输出: start-->>" + method.getName());

        Object ret = null;
        try {
            //调用目标方法
            ret = method.invoke(target, args);
            System.out.println("代理类输出: success-->>" + method.getName());
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("代理类输出: error-->>" + method.getName());
            throw e;
        }
        return ret;
    }

}
