package com.emcloud.cpi.service;

import java.util.LinkedList;

/**
 * 实现缓冲区
 *
 * @author Capejor
 *
 */
public class JmsBuffer {
    // 队列 最大存储量
    private final static int  MAX_SIZE = 100;
    // 消息队列
    private static LinkedList<String> jmsQueue=new LinkedList<String>();
    static JmsBuffer buffer;
    // 生产消息
    public static void produce(String str)
    {
        // 同步代码段
        synchronized (jmsQueue)
        {
            // 如果仓库剩余容量不足
            while (jmsQueue.size()> MAX_SIZE)
            {
                System.out.println("你要生产的消息为" + "/t【库存量】:"
                    + jmsQueue.size() + "/t暂时不能执行生产任务!");
                try
                {
                    // 由于条件不满足，生产阻塞
                    jmsQueue.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            // 生产条件满足情况下，生产消息

            jmsQueue.add(str);

            System.out.println("已经生产该消息" + str + "/t【现仓储量为】:" + jmsQueue.size());

            jmsQueue.notifyAll();
        }
    }

    // 消费消息
    public static String consume()
    {
        // 同步代码段
        synchronized (jmsQueue)
        {
            // 如果仓库存储量不足
            while (jmsQueue.size() > MAX_SIZE)
            {
                System.out.println("【消息库存量】:"
                    + jmsQueue.size() + "/t暂时不能执行生产任务!");
                try
                {
                    // 由于条件不满足，消费阻塞
                    jmsQueue.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            // 消费条件满足情况下，消费该消息

            String str=(String) jmsQueue.removeLast();

            System.out.println("【已经消费该消息】:" + str+ "/t【现仓储量为】:" + jmsQueue.size());

            jmsQueue.notifyAll();
            return str;
        }
    }
    public synchronized static JmsBuffer getJmsBuffer(){
        if(buffer==null){
            return new JmsBuffer();
        }else{
            return buffer;
        }
    }

}
