package com.yu.config;
/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/25 10:54 
 */
public class AlipayConfig {

    // 作为身份标识的应用ID
    public static String app_id = "2021000116682104";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDKyuEGNQ7vIsuK8oyiu7+ahwJ2tvWaZTrB+cttDs4MjQvANpppALNrHNahbkAga52tuswAYimVVpAjAFFridiCg34017hfwQHAw2SgFVBamjB1NCmrYkwEyJGaY7InP4yThWwTG9p4I4bLgTz7Yj5xvXKIy0pl1hStqtO1WKGBU+Q9tJ+4y2PEpSys7iE9ZSbN0zLxqJzSJ0W76s3uckx7MmovYleiXkxu9zBkcfY1924cqzjiBRDQBVhCAnpiYMhcQwDF9spbqZb8kyPKH+Oc25S2wJeEdlBO3+CF3LfBdR/GsJuPphpwLF0Owrxg8OPAS9eRbwhJWz+R5tOkUEX3AgMBAAECggEBAIPpMDKfdNVs5sW9PGnmgp370Eh0fpIt4uimmgKYrMNRRCGF4y8GO68jFSHw21m7ZtfJOEXPlIkFw8BgxpWTFvjem8u3vsDahU9kSiIvexNUVM0IX0qSKCy8Yqnwy8Pn/INBOKm9ZoDbayUZLhe/Mis+NjBxPDW96bmF8yx1OKksReQ+rx8O/L92csvOinZMJplKc0frmsqBOeSirl+kTDDMVeL1qndiBBGWhZ+D8x/3GgOc54qFAU9UdbBVTXrsFppgPBYJQ1aZexWWeuJKxc5gN1d/TRKLpExe/pgV0x3nOrB6DNzoxQl2I7wMkxa7bIT3XRFI71yasvfHsLE7RrECgYEA8GESuWeBAaHeSRNlIj/Obvrk6Hx9hnVHla9N+3PJXWUIdOu/2VKyFfSn6ll6A6bnhAttgMg7gRMLIHnT0ycTBBkLnfLXEbuVzRR7yA3YZ6WzfraJpBqJHULqWJmFTZzgqGldm3HGyEnwS2w0lqvFNSHkKEYDALMgvCi3K7gPurUCgYEA1/iEVYXMjWgFb/BWdHuGeYeeq8ZswWnCwJ/U54Hcr/pPDcgQL+bLsfVQ2ginMsEn5ijiRxM25HgQRlVt/y6QPBAPMlyGoR82sU2UwVmj+aJymYP88AQJ6TgdAMnhn0c3M9s2Q4NKKj/q64NZwF5nrZuJBnMapNEUKrxdndpI7XsCgYEAzBuhKo0Ynao0dRhi55IGV3XyqPYMH/+1qQQR01Gdyg/f/mevn3j4fmwfqH4RfMhcDWMmuBNNc2wYJptLoh+rpe8eTh/FPulpaZnbASPAfUHWB+I3PYDKrG4FTvYMmrp6iIXvVe5mO+uMfn6CqO1xpowAj7HjGtT/FsX7wsOnl4ECgYBl+5lp1SOxhnRC1qfMm2P10V/Q14MVlf57/T2JEJ6Ijtdzt4xFQmPfperG+p0Rb3qKybWIQH+ERVnSDJdDj9ZWNnMmoeCFBvtJoEvlPZIb2GTrLxNqt02CzpeLfslG8FcV6qbmHIISD1q8WasxFqM4rKB45jOnrn8qawZ9+szDLwKBgEHMPkXFPWp/6XgjSOD66M7saY1JBCjwJ4UkwgU+jFIBIWs7GC1POtQEErnzN2FCGQkC83C8A4w8dBx2UJw8V+mNgdeWE2dda259R0pYF4MDe5JOzFCYitP0YoqG81tx0TtsNFfodBbQ5z937Q2XVN++DqAUk1Y/g4QISdm5gTaS";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm1ri3uNj3HiQ+rUxhHRJknuSx0FrkSFLkHXqyT5MmlGpIiZ4dVRfNRkp7f27ptmgzy6xBs0jKRPh381Oa4ydqjaDIeSZ+jxWCKsyYRZh3+cPzL91pb/XjCA3khIU6Vgp3IEKRMGq9VsONvqehu1BZeA5INW56Lh4bd8tV/pfPvy+w8MIniRbdsRleFKOSE6AdvljecJH5wzFT4GtsLRsaPG1QgWhJ7d1MnbiZzGSJNyVaGoCa175JC1c72mkdHp0s2/ClEAzlJfcwU78UC9pvBKL3763sNT6hDAibYlWwYjAcGJ0teKc6uaJmG53b7t8QbFQKq0EXQaD//FK8jjXIQIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
   // public static String notify_url = "http://daheng.vaiwan.com/alipay/callback";
    public static String notify_url = " http://haha.vaiwan.com/alipay/callback";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://haha.vaiwan.com/alipay/show";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "UTF-8";

    // 支付宝网关https://openapi.alipay.com/gateway.do
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}
