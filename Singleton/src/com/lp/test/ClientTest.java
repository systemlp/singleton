package com.lp.test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lp.singleton.SimpleSingleton;
import com.lp.singleton.SimpleSingleton2;
import com.lp.singleton.SimpleSingletonSync;
import com.lp.singleton.SingletonEnum.SingletonFactory;
import com.lp.singleton.StaticSingleton;

public class ClientTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        simpleEnum(executorService);
        executorService.shutdown();
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
    
    static void simpleEnum(ExecutorService executorService) {
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

}
