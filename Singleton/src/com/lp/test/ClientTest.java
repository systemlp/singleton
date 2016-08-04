package com.lp.test;

import com.lp.singleton.SimpleSingleton;

public class ClientTest {

    public static void main(String[] args) {
        SimpleSingleton singleton1 = SimpleSingleton.getInstance();
        SimpleSingleton singleton2 = SimpleSingleton.getInstance();
        System.out.println(singleton1 == singleton2);
    }

}
