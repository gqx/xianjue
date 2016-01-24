/*
 * @(#)PaymentThreadFactory  1.0 2015/6/13
 *
 * Copyright 2014-2019 chinabank payment All Rights Reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Author Email: wyjiangchunan@chinabank.com.cn
 */
package xianjue.gqx.schedule;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: gaoqixin 2016/1/24
 *
 */
public class IrrigationThreadFactory implements ThreadFactory {

    private String prefix;

    private final AtomicInteger threadNum = new AtomicInteger(1);

    private final ThreadGroup threadGroup;

    private final boolean daemon;

    public IrrigationThreadFactory(String prefix) {
        this(prefix, false);
    }

    public IrrigationThreadFactory(String prefix, boolean daemon) {
        this.prefix = prefix + "-irrigation-";
        this.daemon = daemon;
        SecurityManager s = System.getSecurityManager();
        threadGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = prefix + threadNum.getAndIncrement();
        Thread ret = new Thread(threadGroup, r, name, 0);
        ret.setDaemon(daemon);

        if(threadNum.intValue() > 999){
            threadNum.set(1);
        }

        return ret;
    }
}

