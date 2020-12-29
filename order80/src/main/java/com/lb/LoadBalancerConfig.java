package com.lb;/**
 *
 * @author yubingqian
 * @date 2020-09-25 11:22
 *
 */

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/25 11:22 
 */
public class LoadBalancerConfig extends AbstractLoadBalancerRule {
    private static Integer total = 0;  //调用次数
    private static Integer serverVersion = 0;   //机器号

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        System.out.println("==============ribbon load balancer start===========================");
        return choose( getLoadBalancer(),o);
    }

    /**
     * 每个服务请求5次，轮询
     * @param lb
     * @param key
     * @return
     */
    @SuppressWarnings(value = "RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE")
    public Server choose(ILoadBalancer lb, Object key) {
        System.out.println(lb);
        System.out.println("=========total=========" + total);
        if (lb == null) {
            return null;
        }
        Server server = null;

        do {
            if (Thread.interrupted()) {
                return null;
            }
            //负载均衡来获得可用实例列表upList和allList
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();
            int serverCount = allList.size();
            System.out.println("serverCount========" + serverCount);
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }
            System.out.println("total========" + total);
            if (total < 4) {
                server = upList.get(serverVersion);
                total++;
            } else {
                total = 0;
                serverVersion++;
                if (serverVersion >= upList.size()) {
                    serverVersion = 0;
                }
            }
            System.out.println("server=======" + server );
            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }
            System.out.println("server is Alive == " + server.isAlive());
            if (server.isAlive()) {
                System.out.println("alive server======" + server);
                return (server);
            }
            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }while (server == null);

        //正常情况下，每次都应该可以选择一个服务实例
        System.out.println("==============ribbon load balancer end===========================");
        return server;
    }


    //随机获取一个随机数
    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }
}