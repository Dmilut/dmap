package ru.dz.vita2d.init;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author dmilut created on Nov 3, 2016
 */

@PropertySource(value = "classpath:db.properties")
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories("ru.dz.vita2d.repository")
@Configuration
public class PersistenceJPAConfig {
	private static final String PROP_DATABASE_DRIVER = "db.driver";
	private static final String PROP_DATABASE_PASSWORD = "db.password";
	private static final String PROP_DATABASE_URL = "db.url";
	private static final String PROP_DATABASE_USERNAME = "db.username";
	private static final String PROP_HIBERNATE_DIALECT = "db.hibernate.dialect";
	private static final String PROP_HIBERNATE_SHOW_SQL = "db.hibernate.show_sql";
	private static final String PROP_HIBERNATE_FORMAT_SQL = "db.hibernate.format_sql";
	private static final String PROP_HIBERNATE_USE_COMMENTS = "db.hibernate.use_sql_comments";
	// private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN =
	// "db.entitymanager.packages.to.scan";
	private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "db.hibernate.hbm2ddl.auto";

	@Resource
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "ru.dz.vita2d.model" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(getHibernateProperties());

		return em;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty(PROP_DATABASE_DRIVER));
		dataSource.setUrl(env.getProperty(PROP_DATABASE_URL));
		dataSource.setUsername(env.getProperty(PROP_DATABASE_USERNAME));
		dataSource.setPassword(env.getProperty(PROP_DATABASE_PASSWORD));
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put(PROP_HIBERNATE_DIALECT, env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
		properties.put(PROP_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
		properties.put(PROP_HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO));
		properties.put(PROP_HIBERNATE_FORMAT_SQL, env.getRequiredProperty(PROP_HIBERNATE_FORMAT_SQL));
		properties.put(PROP_HIBERNATE_USE_COMMENTS, env.getRequiredProperty(PROP_HIBERNATE_USE_COMMENTS));

		return properties;
	}
}
