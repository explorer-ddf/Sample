package com.init;

/**
 * Created by ddf on 16/6/8.
 */
public class Main {
    public static void main(String[] args) {
        new SubClass();

        String clsName1 = SubClass.class.getName();
        String clsName2 = SubClass.class.getSimpleName();
    }

}
