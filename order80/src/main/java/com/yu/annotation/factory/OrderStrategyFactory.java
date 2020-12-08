package com.yu.annotation.factory;

import com.yu.annotation.anconfig.OrderChannel;
import com.yu.annotation.strategy.OrderStrategy;
import org.reflections.Reflections;
import java.util.HashMap;
import java.util.Set;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/8 15:26 
 */
public class OrderStrategyFactory {
    //饿汉式 单例模式
    private static OrderStrategyFactory orderStrategyFactory = new OrderStrategyFactory();

    /**
     * 单例模式-私有构造器
     */
    private OrderStrategyFactory(){

    }
    public static OrderStrategyFactory getInstance(){
        return orderStrategyFactory;
    }

    /**
     * 重点：存储所有的payChannel
     */
    public static HashMap<Integer,String> orderChannelMap=new HashMap<>();

    static {
        //1、扫描订单渠道的实现类 ,Reflections 依赖 Google 的 Guava 库和 Javassist 库
        Reflections reflections = new Reflections("com.yu.annotation.strategyImpl");
        //2、获取所有包含orderChannel注解的类
        Set<Class<?>> classList = reflections.getTypesAnnotatedWith(OrderChannel.class);
        for (Class clazz : classList) {
            OrderChannel t = (OrderChannel) clazz.getAnnotation(OrderChannel.class);
            //3、赋值orderChannelMap，存储所有的支付渠道
            orderChannelMap.put(t.channel(), clazz.getCanonicalName());
        }

    }
    /**
     *  根据channelId获取对应的具体实现
     * @param channelId
     * @return
     */
    public OrderStrategy getStrategy(Integer channelId)  {
        try {
            //1.获取渠道对应的类名
            String clazz=orderChannelMap.get(channelId);
            Class clazz_=Class.forName(clazz);
            /**
             * newInstance ：工厂模式经常使用newInstance来创建对象，newInstance()是实现IOC、反射、依赖倒置 等技术方法的必然选择
             *      调用class的加载方法加载某个类，然后实例化
             *
             * new 只能实现具体类的实例化，不适合于接口编程
             */
            return (OrderStrategy) clazz_.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
