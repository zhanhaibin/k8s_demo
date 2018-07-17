package com.ava.k8s_demo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.aliyuncs.utils.Base64Helper;

public class K8sAliyun {
	private static final String ENCODING = "UTF-8";
	private static String percentEncode(String value) throws UnsupportedEncodingException {
	  return value != null ? URLEncoder.encode(value, ENCODING).replace("+", "%20").replace("*", "%2A").replace("%7E", "~") : null;
	}
	
	private static final String ISO8601_DATE_FORMAT = "YYYY-MM-dd'T'HH:mm:ss'Z'";
	private static String formatIso8601Date(Date date) {
//	  TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
	  SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
	  df.setTimeZone(new SimpleTimeZone(0, "GMT"));
	  return df.format(date);
	}
	

	public void GetHttps() {
		String Https_Url = "https://ecs.aliyuncs.com/?";
		String HttpsRequestUrl = "";
		String HTTP_METHOD = "GET" ;
		 Map<String,String> param=new HashMap<String,String>();
		 //API 的名称。
		 param.put("Action","DescribeClusters");	
		 //访问密钥 ID。AccessKey 用于调用 API
		 param.put("AccessKeyId","LTAI3m9rYhH66Iyz"); 			
		 //签名方式。取值范围：HMAC-SHA1
		 param.put("SignatureMethod","HMAC-SHA1");
		 //签名算法版本。取值范围：1.0。
		 param.put("SignatureVersion","1.0");	
		//签名唯一随机数。用于防止网络重放攻击，建议您每一次请求都使用不同的随机数。
		 param.put("SignatureNonce","9e030f6b-03a2-40f0-a6ba-157d44532fd1"); 
		 //请求的时间戳。遵循 ISO8601 标准，并需要使用 UTC 时间，格式为 YYYY-MM-DDThh:mm:ssZ。 示例：2018-01-01T12:00:00Z 表示北京时间 2018 年 01 月 01 日 20 点 00 分 00 秒。
		 param.put("Timestamp",formatIso8601Date(new Date()));
		 //API 的版本号，格式为 YYYY-MM-DD。取值范围：2014-05-26
		 param.put("Version","2014-05-26");
		 //返回参数的语言类型。取值范围：json | xml。默认值：xml 
        param.put("Format","json");
		 
        //构造规范化请求字符串Canonicalized Query String
        // 排序请求参数
		String[] sortedKeys = param.keySet().toArray(new String[]{});
		Arrays.sort(sortedKeys);
		final String SEPARATOR = "&";
	    // 构造 stringToSign 字符串
		StringBuilder stringToSign = new StringBuilder();
		StringBuilder canonicalizedQueryString = new StringBuilder();
		for(String key : sortedKeys) {
		// 这里注意编码 key 和 value
		  canonicalizedQueryString.append("&")
		  .append(key).append("=")
		  .append(param.get(key));
		}
		// 这里注意编码 canonicalizedQueryString
		 stringToSign.append(canonicalizedQueryString.toString().substring(1)).toString();
		System.out.println("\n "+stringToSign);
		HttpsRequestUrl = Https_Url+stringToSign+SEPARATOR+GetSignature();	
		System.out.println("\n "+HttpsRequestUrl);
	}
	 public String GetSignature() {
		 try {
			 String HTTP_METHOD = "GET" ;
			 Map<String,String> param=new HashMap<String,String>();
			 //API 的名称。
			 param.put("Action","DescribeClusters");	
			 //访问密钥 ID。AccessKey 用于调用 API
			 param.put("AccessKeyId","LTAI3m9rYhH66Iyz"); 			
			 //签名方式。取值范围：HMAC-SHA1
			 param.put("SignatureMethod","HMAC-SHA1");
			 //签名算法版本。取值范围：1.0。
			 param.put("SignatureVersion","1.0");	
			//签名唯一随机数。用于防止网络重放攻击，建议您每一次请求都使用不同的随机数。
			 param.put("SignatureNonce","9e030f6b-03a2-40f0-a6ba-157d44532fd1"); 
			 //请求的时间戳。遵循 ISO8601 标准，并需要使用 UTC 时间，格式为 YYYY-MM-DDThh:mm:ssZ。 示例：2018-01-01T12:00:00Z 表示北京时间 2018 年 01 月 01 日 20 点 00 分 00 秒。
			 param.put("Timestamp",formatIso8601Date(new Date()));
			 //API 的版本号，格式为 YYYY-MM-DD。取值范围：2014-05-26
			 param.put("Version","2014-05-26");
			 //返回参数的语言类型。取值范围：json | xml。默认值：xml 
             param.put("Format","json");
             //构造规范化请求字符串Canonicalized Query String
            // 排序请求参数
     		String[] sortedKeys = param.keySet().toArray(new String[]{});
     		Arrays.sort(sortedKeys);
     		final String SEPARATOR = "&";
     	    // 构造 stringToSign 字符串
     		StringBuilder stringToSign = new StringBuilder();
     		stringToSign.append(HTTP_METHOD).append(SEPARATOR);
     		stringToSign.append(percentEncode("/")).append(SEPARATOR);
     		StringBuilder canonicalizedQueryString = new StringBuilder();
     		for(String key : sortedKeys) {
     		// 这里注意编码 key 和 value
     		  canonicalizedQueryString.append("&")
     		  .append(percentEncode(key)).append("=")
     		  .append(percentEncode(param.get(key)));
     		}
     		// 这里注意编码 canonicalizedQueryString
     		 stringToSign.append(percentEncode(
     		  canonicalizedQueryString.toString().substring(1))).toString();
     		System.out.println("\n "+stringToSign);
     		// 以下是一段计算签名的示例代码
    		 final String ALGORITHM = "HmacSHA1";
    		 final String ENCODING = "UTF-8";
    		 String key = "bBAyAndvDPRp6ii1g5VJl1PLFNaJ1Y&";
    		 Mac mac = Mac.getInstance(ALGORITHM);
    		 mac.init(new SecretKeySpec(key.getBytes(ENCODING), ALGORITHM));
    		 byte[] signData = mac.doFinal(stringToSign.toString().getBytes(ENCODING));
    		 String signature =Base64Helper.encode(signData);
    		 System.out.println("\n "+signature);
     		 return signature;
		}  catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
           return null;
       } catch (InvalidKeyException e) {
           e.printStackTrace();
           return null;
       }
	 }
	  
	 
}
