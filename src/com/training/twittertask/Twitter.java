package com.training.twittertask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.util.Base64;

public class Twitter {

	static final String CONSUMER_KEY="74qnkcZAwmV7B1JiPaQfx6IjH";
	static final String CONSUMER_SECRET="ErPRuUaMNpjwjgyvbzI1F3nqlKMyvuvxtXFuQMlZTY7JXvCETp";
	static final String TOKEN="367740394-W1SXlR1TTRHkR6LwFFD9C0tCZ16N7XAMnB14lLBA";
	static final String TOKEN_SECRET="KtLYAuTGUNHGcvc0ZoJxutZrkXsiRrZb1y0b4UJZnEvyR";
	static final String URL_UPDATE="https://api.twitter.com/1.1/statuses/update.json";
	
	public static String post(String text) {
		URL url;
		HttpURLConnection uc=null;
		String v = null;
		
		try {
			url=new URL(URL_UPDATE);
			uc = (HttpURLConnection) url.openConnection();
			uc.setDoOutput(true);
			uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			String n= generateAuthorizationString("POST",URL_UPDATE, text, 
				CONSUMER_KEY,CONSUMER_SECRET,TOKEN, TOKEN_SECRET);
				
			uc.setRequestProperty("Authorization", n);
			OutputStream out = uc.getOutputStream();
			out.write("status=".concat(URLEncoder.encode(text, "UTF-8")).getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			InputStream is = uc.getInputStream();
			InputStreamReader ins = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(ins);
			StringBuffer sb=new StringBuffer("");
			String line;
			while ((line=br.readLine()) != null) {
				sb.append(line);
			}
			v=sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	public static String generateAuthorizationString(String method, String url, String status,
		String oauth_consumer_key, String oauth_consumer_secret, String oauth_token,
		String oauth_token_secret) {
		
		StringBuffer sb= null;
		String signature_base_string, parameter_string;
		try {
			sb = new StringBuffer();
			String oauth_version = "1.0", oauth_signature_method="HMAC-SHA1",
				oauth_nonce = "wieurjsliepwkejfntlteoxlpqnasjsqky6d35es3j",
				oauth_timestamp = new Long(System.currentTimeMillis() / 1000).toString();

			parameter_string = "oauth_consumer_key=" + oauth_consumer_key +
					"&oauth_nonce=" + oauth_nonce + 
					"&oauth_signature_method=" + oauth_signature_method +
					"&oauth_timestamp=" + oauth_timestamp +
					"&oauth_token=" + oauth_token +
					"&oauth_version=" + oauth_version;
					if ("POST".equals(method) && !"".equals(status)) {
						parameter_string = parameter_string + "&status=" + URLEncoder.encode(status).replace("+", "%20");
					}
			
			signature_base_string = method+"&" + URLEncoder.encode(url, "UTF-8") + "&" + URLEncoder.encode(parameter_string);
			
			String signature_key = URLEncoder.encode(oauth_consumer_secret, "UTF-8") + "&" +
				URLEncoder.encode(oauth_token_secret);
			String oauth_signature = URLEncoder.encode(
				hashHmac(signature_base_string, signature_key).trim(), "UTF-8");
			sb.append("OAuth");
			sb.append(" oauth_consumer_key=\""); sb.append(oauth_consumer_key); sb.append("\"");
			sb.append(", oauth_nonce=\""); sb.append(oauth_nonce); sb.append("\"");
			sb.append(", oauth_signature=\""); sb.append(oauth_signature); sb.append("\"");
			sb.append(", oauth_signature_method=\""); sb.append(oauth_signature_method); sb.append("\"");
			sb.append(", oauth_timestamp=\""); sb.append(oauth_timestamp); sb.append("\"");
			sb.append(", oauth_token=\""); sb.append(oauth_token); sb.append("\"");
			sb.append(", oauth_version=\""); sb.append(oauth_version); sb.append("\"");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return(sb.toString());
	}
	
	public static String hashHmac(String value, String key) {
		String v = null;
		try {
			javax.crypto.Mac mac= javax.crypto.Mac.getInstance("HmacSHA1");
			mac.init(new javax.crypto.spec.SecretKeySpec(key.getBytes(),
				"HmacSHA1"));
			v = Base64.encodeToString(mac.doFinal(value.getBytes()),
				Base64.DEFAULT);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return (v);
	}

}
