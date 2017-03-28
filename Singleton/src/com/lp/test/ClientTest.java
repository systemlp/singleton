package com.lp.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lp.singleton.SimpleEnum;
import com.lp.singleton.SimpleSingleton;
import com.lp.singleton.SimpleSingleton2;
import com.lp.singleton.SimpleSingletonSync;
import com.lp.singleton.SingletonEnum.SingletonFactory;
import com.lp.singleton.StaticSingleton;

public class ClientTest {

    public static void main(String[] args) throws InterruptedException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        simpleEnum(executorService);
        executorService.shutdown();
        // 反射测试
        // Class<SimpleEnum> clazz = SimpleEnum.class;
        // Constructor<?> constructor = clazz.getDeclaredConstructor(null);
        // constructor.setAccessible(true);
        // SimpleEnum enum1 = (SimpleEnum) constructor.newInstance();
        // SimpleEnum enum2 = SimpleEnum.INSTANCE;
        // System.out.println(enum1 == enum2);

        Class<SimpleSingletonSync> clazz = SimpleSingletonSync.class;
        Constructor<?> constructor = clazz.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        SimpleSingletonSync enum1 = (SimpleSingletonSync) constructor.newInstance();
        SimpleSingletonSync enum2 = (SimpleSingletonSync) constructor.newInstance();
        System.out.println(enum1 == enum2);
    }

    static void simple(ExecutorService executorService) {
        System.out.println("------------最简单的单例，不适用多线程------------");
        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(SimpleSingleton.getInstance().hashCode());
                }
            });
        }
    }

    static void simple2(ExecutorService executorService) {
        System.out.println("------------饿汉式单例，类加载时初始化，不适合初始化耗时较长的对象------------");
        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(SimpleSingleton2.getInstance().hashCode());
                }
            });
        }
    }

    static void simpleSync(ExecutorService executorService) {
        System.out.println("------------最简单单例中初始化操作加锁------------");
        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(SimpleSingletonSync.getInstance().hashCode());
                }
            });
        }
    }

    static void simpleStaticClass(ExecutorService executorService) {
        System.out.println("------------静态内部类单例，使用时才初始化------------");
        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(StaticSingleton.getInstance().hashCode());
                }
            });
        }
    }

    static void singleEnum(ExecutorService executorService) {
        System.out.println("------------枚举创建对象单例------------");
        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(SingletonFactory.INSTANCE.getInstance().hashCode());
                }
            });
        }
    }

    static void simpleEnum(ExecutorService executorService) {
        System.out.println("------------枚举代替类实现单例，可抵御反射------------");
        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(SimpleEnum.INSTANCE.hashCode());
                }
            });
        }
    }

}
