package com.cn.ub.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ @author lichuan
 *
 */
public class ContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		ContextUtil.context = arg0;

	}

	public static Object getSpringBean(String beanName) {
		return context == null ? null : context.getBean(beanName);
	}

	public static String[] getBeanDefinitionNames() {
		return context.getBeanDefinitionNames();
	}

}
