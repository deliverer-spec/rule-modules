package com.ciana.rule.engine.config;

import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 读取engine.properties
 * @author admin
 *
 */
public class Config {
	
	private static volatile Config config = null;
	private Map<String, String> configCache = new HashMap<>();
	
	private Config() {}
	
	public static synchronized Config getInstance() throws Exception{
		if(config == null) {
			config = new Config();
			init();
		}
		
		return config;
	}

	public String getConfig(String key) {
		return configCache.get(key);
	}
	private static void init() throws Exception {
		Properties properties = new Properties();
       	
       	properties.load(new FileInputStream(getPropertiesFile()));
       	
       	Set<Object> set = properties.keySet();
		Iterator<Object> it = set.iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			String value = properties.getProperty(key);
			config.configCache.put(key, value);
		}
	}
	
	private static String getPropertiesFile() throws Exception {
		ClassLoader classLoader = Config.class.getClassLoader();
		URL url = classLoader.getResource("engine.properties");
//		System.out.println("url:\t"+ (url == null ? "null": url.getPath()) + ";classLoader is:"+ classLoader.toString());
		URL url2withSlash = classLoader.getResource("/engine.properties");
//		System.out.println("url/:\t"+ (url2withSlash == null ? "null": url2withSlash.getPath()));
		if(url != null) {
			return url.getPath();
		}else if(url2withSlash != null) {
			return url2withSlash.getPath();
		}
		throw new Exception("没有找到配置文件engine.properties");
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(Config.getPropertiesFile());
		System.out.println(Config.getInstance().getConfig("rule.engine.connimpl"));
	}
}
