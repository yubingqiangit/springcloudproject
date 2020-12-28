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
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCS8H6XJPHaaASDYWigmC9OTIK3jFtYwyTGRBUZ/YcEwjTc/9XMwCJZ9j3GWUswzBznDPSYbRDM7dpGX68K45tHie4Ds0/gx8LhFPr3LwqtZo1L16BBX2kjybfHriGYdT3VoYvviHHViydoLvG6CDdRGuJxmpIBjzocjoEDXCtUCT5bHk+Ln/t7LToRyJyvxyrEuAfUmbI3NfejCzRLThcZgB+OJuvGLlFCjJOcvB8jnHxnXfXK6gdbtzbrjZ8zB3BkZiETYIdMVLVjN5KyrHLJ0gnfcwEszGoVcETj8pvPuYE1AD47PN06G+vT6xWSxi4ClvYkv4336VirctC5IZBpAgMBAAECggEAB+4k1uFukBwHClQqgmhUPUSR1XgkwfCePnkULnfpXo9grQC9uDQyZmqMwPS2wyZw9ONVHBjezECWBDBL0SmhisXd66M0z2vYj69DMC4AxIUBYTN8+ao9kWnkmklClOjW8n2qgL37bsv4z32hHCovWhg6Wmk+MglZ2Iq0WcsSp5FW6cVMMRrnqKt4VG4FSI9pluDiRM2bVrsxBf3hyGrv5QFK69ejM/aBZNlIkZvcedYPb6td2LM2tABVqdEoZVOKOEDBkdFbgthhYD5WOpnHOY1rRQsgW+AEzbN43z8WzzHWr7C2qeTCpQ92P7VG1FMSsTETdsxiqC8gwCeMjSYWlQKBgQDlfaIsV8lciBlBpm5CSa3HYLwokeRGMthYZtX7ClTHNbO4g6ZTsHJeS50ISuV8XJm3qHT+yw8oopWIFqBSnDB/CFbgY25ci7MhU9Tz+Q/hq0lFU/L/NGqvtuWkI3ZRkukT8oktVhd1L/kInofu4ulse0rWKm+wbW+6cVb+6kTzzwKBgQCj6bOU4PV4jDhN1RJJIYHRh0UbkumGDG8Cqz3/Zv4Q/27lVJOgSuFqN6otF3iJ5r7uT2PlskL+DBLeqZXsgkn73o7wmJK/1f2N72pCA4I76CHJgWkt+c1N977FUrD6GEn1JY61OJEjSzI1j+TQJu5FKhd1dweZdvLbAPsgSZFuRwKBgCGOiFsxjvkf0Yme+3pn6ahAxeKsypNVNftfNiTaMuFaQehWH8kXAPI23kRrJzlTFQsc79ad/J51f6YBoPnTauf4YTO4dlfD+bKC3p26Ko9WC4W+q8aTdJIzDUs+ADwXXkuTaGmfsBhrpl+TRs8wkO9S6/N7Cw/iQLcjgZQOz3xTAoGBAIUxNDH4Er+gAWiNiUxPo/Ok3a0oVgYNOG+MwtY01KLvTSeg8EzCTCVJkkkN128HR4LSlDRkBDf3i6XPW/pRbNjz4mbzM9wagOzC7S8zNrKDUGEzfUpUwFVoyWYCabjv37kKicoMazOouOX5RU83M0qUh3d1/4DevTzksSttXHWlAoGBAK0E6/ES0vP3NQOL3dHB9CtriBZK3cFtIIgLykaeLFW91smWLfEp9LsuP9Ksu8v6suviHBs52y/WPGuX0SLLEb2DjpzCfEJLf9gmOnDNen+tKNFo3JLZFG4l2i+qo+uJV4Xg81opeNfkOy7F+rW46OkCtqXc9D51nUJlx0/2IUBg";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm1ri3uNj3HiQ+rUxhHRJknuSx0FrkSFLkHXqyT5MmlGpIiZ4dVRfNRkp7f27ptmgzy6xBs0jKRPh381Oa4ydqjaDIeSZ+jxWCKsyYRZh3+cPzL91pb/XjCA3khIU6Vgp3IEKRMGq9VsONvqehu1BZeA5INW56Lh4bd8tV/pfPvy+w8MIniRbdsRleFKOSE6AdvljecJH5wzFT4GtsLRsaPG1QgWhJ7d1MnbiZzGSJNyVaGoCa175JC1c72mkdHp0s2/ClEAzlJfcwU78UC9pvBKL3763sNT6hDAibYlWwYjAcGJ0teKc6uaJmG53b7t8QbFQKq0EXQaD//FK8jjXIQIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://daheng.vaiwan.com/alipay/callback";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://daheng.vaiwan.com/alipay/show";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "UTF-8";

    // 支付宝网关https://openapi.alipay.com/gateway.do
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}
