package com.yu.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;

/**
 * 自定义feign配置
 * 不能加@Configruation注解，如果加了不能放在主应用程序扫描的包中
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/28 14:54 
 */
public class FeignConfigruation {

    /**
     * 将契约修改feign原生默认契约，这样就可以使用feign的自带注解了
     * @return
     */
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    /**
     * 接口需要http basic认证后才能调用
     * @return
     */
    @Bean
    public BasicAuthenticationInterceptor basicAuthenticationInterceptor() {
        return new BasicAuthenticationInterceptor("admin", "admin");
    }
}
