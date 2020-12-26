/**
 * 
 */
package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

/**
 * @author lh
 * @data 2020年12月14日
 * Email 2944862497@qq.com
 */
public class AlipayConfigInfo {
	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000116669457";//例：2016082600317257
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCXIm5H4r1L6lLzNJXj6M2QkANCcfbn+g/dbgMAeovJ6SN0npus97GPJ0YuHQ/AQWoEuWNQQa7uUiP0P3FCseSA52g4VY2ldIcmeBxI3O/hidNHxsZZp40D0e9tnYLlSCG7i/pRMFibuDk9T8h+/X9xy3ECPu8rhwJlJxlf4g0HHbbha5iiVoxAFrRGEFph7HJyvB2nY+pxeXlBgjhsHU30MCPa6I12J0S7kfPks/JGQXuks7tLdKoJLPTbfuvOGEysUQnScIQqQdK8rZjfAX+ItCQNBDxE26BXPYgUYlH9t7gWjKneWTKY+Tb4bxJFt2DlrOqgoafr1XgthLO1OrFLAgMBAAECggEAeibI7W70QmxkmM8Sqr8ZjlLnX4fO4JV6xyRIZtWlMA8vqe0bs8QKjcJXdaPlfCUitCjGfTJpWZOoHZw9RYOURR9DW+zB7E1vgocnjTiM5URJMMUWwZWrYsAF/RmGlxwir3umWIJzy4AtO0HFseXfYLDmaMNG+W5IYEsmthl1IB8E3W7rBSqIE179qBaDsF9yqCzdo4FbsgfoAC2VcT1HpxxLVyZuHJBsbbUWoZgHZgLsFgFxF5RxCtfL6xBHRRdt1k8bUKRnD8WOOJ25Bk523ix6dGg8z88AJddDQLVixjSP0lwXqq9xWnCngv8u//H99YaE6xn0Nj3+C8nq2bsL6QKBgQDMzpn5vdHRXCnK14gXB+CakNe7pTSm/E9lQgW1kCs28rtbbgw2CRsuO3MFUNPNH2KsY0nInKM/2ojb1Ybs/T7v1F+0Ec1rgIqRaqk4Yn0thZ4Jx1xtaK2w8O3Uxae6Zo+sp231GM/pfwWGsmhrzIh+0fu7OfPj/sbm43DvJxSmnQKBgQC86WB0E5lbLr66N7hgeKEBJ0DGyOdbX9fQsWk4OAuB2OcRKjC7/wfb6f2Iv16Ynz6l132uQwPZX77G8yDxo6R666+UKtHfJ3BdUTSWhPUx0PYiT1jhBvPY7Axt4aPJo2gQClFypbrLdn6UewV+xjxmyf287FxQIX5/sEa+TFe/BwKBgQCeQ/lQuJLvSl0xVqTfx0KVruBHLT8m20svYHmw+Jusdp655KyjBR5v95jGvtMta9eAh6l40TKGIYYo+zSs5nZANslOCYPIUyjBfr8Nx24cQ2kA/YI7Oe0fEs924NbggGj+btPV1zqzoWjP0gZHZ2MDPZld8Ua7JSLnQVMAPB8FMQKBgETf+ZCYjLMX6eXO5uQVVu4qt41CNke0O+UUqb4bgOaIhEZX0YjZRK5DM3KmAwE2Hgss89+TA1Y/eDjk77/p39AJGey1Z+sqX+dF8hxmwVa2TubXcxooBMOz3Bw7xhGACDLA8E0Sa3Y+74uHBsvfzqnTPzWgH0//xjUbuc8r2loFAoGAdugVoHxuh9q+MeAA3X/gyAITtsjkKwVQGs5VMuACCNQi2jNnEU83iyuO2CnpieU647Rp6rmoDGYjbeTZtVMwQjdlos4HpsLz5o8bTa52GagPp58z3Tv+pruOkydbSnj3od7fxlUTFnJm78vkzc9jc/24CLFCfCuU1UZ0OEcQezM=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
    // 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkJmoqDUtvfTz+wR3X22YxpkmziCQhrXKb0mem/LY13elA1Aq+qs6r2mFSJijrXLjNndkO5RP7vGA6+H+negFDvQwiU1DduTHF3Gl6IS+P8umgOvZOe65XnSntwTxXw1mD7e2lyqAhrHM4iqJv7nVsqJrPZSO3sJAHlmMwkWU8oupXZKOpsFeJf+XbcLh2Y47qHr7XCoZJmVUSvQaMsMvx1ePQzeYOcZNaP0S2slIglQ7edO1fjHnjh9QOVuXCYxGxQmG8pwOOw/dXJzXUKDbPToSVxnSmPhTuDA+cMPyPjdMkQbZxaNLN7pZIkcLcyyqN+FaeH5nuW8OL/hLHyeqUQIDAQAB";
    // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    /**
     * 返回的时候此页面不会返回到用户页面，只会执行你写到控制器里的地址
     */
    public static String notify_url = "http://xjfque.natappfree.cc/haier/notify";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    /**
     * 此页面是同步返回用户页面，也就是用户支付后看到的页面，上面的notify_url是异步返回商家操作，谢谢
     * 要是看不懂就找度娘，或者多读几遍，或者去看支付宝第三方接口API，不看API直接拿去就用，遇坑不怪别人
     */
    public static String return_url = "http://xjfque.natappfree.cc/haier/index.html";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";  
}
