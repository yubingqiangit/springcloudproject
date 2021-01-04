package com.yu.config;/**
 *
 * @author yubingqian
 * @date 2020-09-22 16:20
 *
 */

import com.lb.LoadBalancerConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/22 16:20 
 */
@Component
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    /**
     * 更改 负载均衡策略算法
     * RandomRule #配置规则 随机
     * RoundRobinRule #配置规则 轮询
     * RetryRule #配置规则 重试
     * WeightedResponseTimeRule #配置规则 响应时间权重
     * 也可以自定义负载均衡策略的类
     * @return
     */
   /* @Bean
    public IRule ribbonRule() {
       // return new RandomRule(); 随机策略
        return new LoadBalancerConfig();
    }*/
}
