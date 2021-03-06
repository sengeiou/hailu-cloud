package com.hailu.cloud.common.redis.lock;

/**
 * redis 锁
 *
 * @author zhijie
 */
public interface IRedisRedLock {

    /**
     * 初始化配置
     *
     * @throws Exception
     */
    void initConfigure() throws Exception;

    /**
     * 给代码上锁
     *
     * @param lockName 锁名称
     * @param callback 获取锁成功后回调
     * @return true 执行成功、 false执行失败
     */
    boolean lock(String lockName, Runnable callback);
}
