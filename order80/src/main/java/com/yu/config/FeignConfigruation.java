package com.yu.config;

import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * 自定义feign配置
 * 不能加@Configruation注解，如果加了不能放在主应用程序扫描的包中
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/28 14:54 
 */
public class FeignConfigruation implements RequestInterceptor {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    /**
     * 将契约修改feign原生默认契约，这样就可以使用feign的自带注解了
     * @return
     */
    @Bean
    public Contract feignContract() {
       // return new SpringMvcContract();
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


    // 记录请求和响应的头文件，正文和元数据。
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    // 连接超时时间 s 读取超时时间 s 是否重定向
    @Bean
    public Request.Options options() {
        return new Request.Options(10 * 1000, TimeUnit.MINUTES, 60 * 1000, TimeUnit.SECONDS, true);
    }

    // 编码方式
    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    // 解析方式
    @Bean
    public Decoder feignDecoder() {
        return new SpringDecoder(messageConverters);
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {

    }
}
