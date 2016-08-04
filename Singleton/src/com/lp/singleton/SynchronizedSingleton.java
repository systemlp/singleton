package com.lp.singleton;

/**
 * 
 * 加入了线程锁的单例<br>
 * 〈功能详细描述〉
 *
 * @author admin
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SynchronizedSingleton {

    private static SynchronizedSingleton instance = null;

    private SynchronizedSingleton() {

    }

    /**
     * 
     * 功能描述:每次调用都会加锁，性能大大下降，仅需第一次创建对象时进行加锁 <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static synchronized SynchronizedSingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }

    /**
     * 
     * 功能描述:只在第一次创建对象时进行加锁，但是第一次有多个线程需要对象时，有可能返回的是null <br>
     * A、B线程需要该对象实例，如果A线程给instance分配空间，但还未实例对象时，资源被B抢去了，B线程进来发现instance不为空<br>
     * 但对象还未被初始化，这时B获取的就会是null
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static SynchronizedSingleton geInstance2() {
        if (instance == null) {
            synchronized (instance) {
                instance = new SynchronizedSingleton();
            }
        }
        return instance;
    }

    /**
     * 
     * 功能描述:将创建对象的代码抽取成单独的方法，并对整个方法加锁，这时就不会出现上述问题了 <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static SynchronizedSingleton getInstance3() {
        if (instance == null) {
            newInstance();
        }
        return instance;
    }

    private synchronized static void newInstance() {
        instance = new SynchronizedSingleton();
    }

}
