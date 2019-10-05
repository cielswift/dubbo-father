package com.ciel.provider.alipay;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("myalipay")
public class AlipayConfig {
	
	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	private  String APP_ID ;
	
	// 商户私钥，您的PKCS8格式RSA2私钥，这些就是我们刚才设置的
    private  String MERCHANT_PRIVATE_KEY;
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。，这些就是我们刚才设置的
    private  String ALIPAY_PUBLIC_KEY  ;

    //异步通知，再这里我们设计自己的后台代码
    private  String notify_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private  String return_url;

	// 签名方式
	private  String SIGN_TYPE;
	
	// 字符编码格式
	private  String CHARSET;
	
	// 支付宝网关
	private  String GATEWAYURL;
	
	// 支付宝网关
	private  String LOG_PATH ;



}
