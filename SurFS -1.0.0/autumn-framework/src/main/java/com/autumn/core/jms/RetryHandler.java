package com.autumn.core.jms;

/**
 * <p>Title: 磁盘缓存</p>
 *
 * <p>Description: 处理消息接口</p>
 *
 * <p>Copyright: Autumn Copyright (c) 2011</p>
 *
 * <p>Company: Autumn </p>
 *
 * @author 刘社朋
 * @version 2.0
 *
 */
public interface RetryHandler<V> {

    /**
     * 处理消息
     *
     * @param message
     * @throws Throwable 如果抛出错误，表示处理失败
     */
    public void doMessage(MessageWrapper<V> message) throws Throwable;
}
