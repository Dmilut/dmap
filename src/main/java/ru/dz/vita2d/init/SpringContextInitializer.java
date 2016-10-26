package ru.dz.vita2d.init;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dmilut
 */

public class SpringContextInitializer {	
	private static volatile SpringContextInitializer instance;
	private ApplicationContext applicationContext;

	private SpringContextInitializer() throws ExceptionInInitializerError {
		try {
			this.applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		} catch (BeansException ex) {
			System.err.print("error " + ex);
		}
	}

	public static SpringContextInitializer getInstance() {
		SpringContextInitializer localInstance = instance;
		
		if (localInstance == null) {
			synchronized (SpringContextInitializer.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new SpringContextInitializer();
				}
			}
		}
		return localInstance;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
