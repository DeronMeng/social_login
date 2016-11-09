package com.social.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class AppConfig extends PropertyPlaceholderConfigurer {
	private static Logger log = Logger.getLogger(AppConfig.class);
	
	private static Map<String, String> propertiesMap;
	private static Pattern placeholderPattern = Pattern.compile("\\{.*?\\}");
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		super.processProperties(beanFactory, props);

		propertiesMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String valueStr = props.getProperty(keyStr);

			if (valueStr != null) {
				Matcher matcher = placeholderPattern.matcher(valueStr);
				StringBuilder value = new StringBuilder(valueStr.length());
				int index = 0;
				while (matcher.find()) {
					int start = matcher.start();
					int end = matcher.end();
					String template = valueStr.substring(start + 1, end - 1);
					String templateVal = props.getProperty(template);
					if (templateVal == null || templateVal.isEmpty()) {
						value.append(valueStr.substring(index, end));
					} else {
						value.append(valueStr.substring(index, start));
						value.append(templateVal);
					}
					index = end;
				}
				value.append(valueStr.substring(index));
				valueStr = value.toString();
			}
			propertiesMap.put(keyStr, valueStr);
		}
		log.info("=========================AppConfig -> PropertiesMap" + propertiesMap);
	}
	
	public static String getProperty(String name) {
		return propertiesMap.get(name);
	}
}
