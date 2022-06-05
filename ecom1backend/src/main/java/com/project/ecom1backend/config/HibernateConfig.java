package com.project.ecom1backend.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages={"com.project.ecom1backend.dto"})
@EnableTransactionManagement
@PropertySource("classpath:connection.properties")
public class HibernateConfig {

	

	@Value("${jdbc.url}")
	private String DATABASE_URL;
	@Value("${jdbc.driver}")
	private String DATABASE_DRIVER;
	@Value("${jdbc.dialect}")
	private String DATABASE_DIALECT;
	@Value("${jdbc.username}")
	private String DATABASE_USERNAME;
	@Value("${jdbc.password}")
	private String DATABASE_PASSWORD;
	
	
	@Bean("dataSource")
	public DataSource getDataSource() {
		
		
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName(DATABASE_DRIVER);
		datasource.setUrl(DATABASE_URL);
		datasource.setUsername(DATABASE_USERNAME);
		datasource.setPassword(DATABASE_PASSWORD);
		return datasource;
	}
	
	@Bean
	public SessionFactory getSessionFactory(DataSource datasource) {
		
		
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(datasource);
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.project.ecom1backend.dto");
		return builder.buildSessionFactory();
	}
	
	public Properties getHibernateProperties() {

		Properties properties = new Properties();
		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto","update");
		return properties;
	}

	
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}
