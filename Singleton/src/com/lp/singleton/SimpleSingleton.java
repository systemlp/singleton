package com.lp.singleton;

/**
 * 
 * 最简单的单例，但存在很多漏洞，如多线程时……<br>
 * 〈功能详细描述〉
 *
 * @author admin
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SimpleSingleton {

    private static SimpleSingleton instance = null;
    
    private SimpleSingleton() {
        
    }

    public static SimpleSingleton getInstance() {
        if (instance == null) {
            instance = new SimpleSingleton();
        }
        return instance;
    }

}
