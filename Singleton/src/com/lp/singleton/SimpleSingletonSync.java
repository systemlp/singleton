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
public class SimpleSingletonSync {

    private volatile static SimpleSingletonSync instance = null;

    private static boolean flag;

    private SimpleSingletonSync() {
        synchronized (SimpleSingletonSync.class) {
            if (!flag) {
                flag = !flag;
            } else {
                throw new RuntimeException("请正常使用该单例类");
            }
        }
    }

    public static SimpleSingletonSync getInstance() {
        if (instance == null) {
            synchronized (SimpleSingletonSync.class) {
                if (instance == null) {
                    instance = new SimpleSingletonSync();
                }
            }
        }
        return instance;
    }

}
