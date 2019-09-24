package com.ddf.annotation;

/**
 * Created by ddf on 16/2/2.
 */
@Description(value = "这是一个有用的工具类")
//等价于：@Description("这是一个有用的工具类")

public class Utility {
    @Author(name = "wangsheng", group = "developer team")
    public String work() {
        return "work over!";
    }
}
