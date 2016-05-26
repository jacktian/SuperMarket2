package com.wuyin.supermarket.manager;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wuyin on 2016/5/2.
 * 管理线程池
 */
public class ThreadManager {

    ThreadPoolProxy poolProxy;

    private static ThreadManager instance = new ThreadManager();

    private ThreadManager() {

    }

    public static ThreadManager getInstance() {
        return instance;
    }

    public class ThreadPoolProxy {
        ThreadPoolExecutor mPoolExecutor;



        //线程池的数量
        private int corePoolSize;
        private int maximunPoolSize;
        private long aliveTime;

        /**
         * @param corePoolSize    线程池的大小
         * @param maximunPoolSize 如果排队满了额外开启的线程的个数
         * @param time            线程存活的时间（单位/毫秒）
         */
        public ThreadPoolProxy(int corePoolSize, int maximunPoolSize, long time) {
            this.corePoolSize = corePoolSize;
            this.maximunPoolSize = maximunPoolSize;
            this.aliveTime = time;
        }


        /**
         * 任务执行
         *
         * @param runnable
         */
        public void execute(Runnable runnable) {
            if (mPoolExecutor == null) {
                //创建线程池
                /**
                 * 1、线程池中有几个线程
                 * 2、如果排队满了，额外开的线程
                 * 3、如果这个线程池没有要执行的任务，存活多久
                 * 4、时间的单位
                 * 5、如果这个线程池里管理的线程都已经用了，剩下的任务  临时存在LinkedBlockingDeque中
                 */
                mPoolExecutor = new ThreadPoolExecutor(
                        corePoolSize, maximunPoolSize, aliveTime, TimeUnit.MILLISECONDS
                        , new LinkedBlockingQueue<Runnable>(10)
                );
            }
            mPoolExecutor.execute(runnable); //调用功能线程池，执行异步任务

        }

        /**
         * 取消任务
         *
         * @param runnable 任务对象
         */
        public void cancel(Runnable runnable) {
            //线程不为空   没有挂起
            if (mPoolExecutor != null && !mPoolExecutor.isShutdown() && mPoolExecutor.isTerminated()) {
                mPoolExecutor.remove(runnable);
            }
        }


    }

    /**
     * 创建线程池  cpu核数*2+1
     *
     * @return
     */
    public synchronized ThreadPoolProxy createLongPool() {
        return createShortPool(5, 5, 5000);
    }

    /**
     * @param size    线程池的大小
     * @param aliSize 如果排队满了额外开启的线程的个数
     * @param time    线程存活的时间（单位/毫秒）
     * @return
     */
    public synchronized ThreadPoolProxy createShortPool(int size, int aliSize, long time) {
        if (poolProxy == null) {
            poolProxy = new ThreadPoolProxy(size, aliSize, time);
        }
        return poolProxy;
    }


}
