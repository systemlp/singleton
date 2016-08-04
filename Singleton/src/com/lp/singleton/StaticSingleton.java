package com.lp.singleton;

/**
 * 
 * 使用静态内部工厂类初始化对象<br>
 * 类在加载时，就被初始化了实例，可避免多线程竞争
 *
 * @author admin
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class StaticSingleton {

    private StaticSingleton() {

    }

    private static class SingletonFactory {
        private static StaticSingleton instance = null;
    }

    public static StaticSingleton getInstance() {
        return SingletonFactory.instance;
    }

}
