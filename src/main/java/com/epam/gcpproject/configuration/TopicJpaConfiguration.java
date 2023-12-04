package com.epam.gcpproject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import com.epam.gcpproject.model.domain.Post;
import com.google.common.base.Preconditions;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackageClasses = Post.class,
  entityManagerFactoryRef = "topicsEntityManagerFactory",
  transactionManagerRef = "topicsTransactionManager"
)
public class TopicJpaConfiguration {

	 @Autowired
	    private Environment env;

	    // beans

	    @Bean
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	        em.setDataSource(dataSource());
	        em.setPackagesToScan("com.baeldung.persistence.model");

	        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	        em.setJpaVendorAdapter(vendorAdapter);
	        em.setJpaProperties(additionalProperties());

	        return em;
	    }

	    @Bean
	    public DataSource dataSource() {
	        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
	        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
	        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
	        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));

	        return dataSource;
	    }

	    @Bean
	    public PlatformTransactionManager transactionManager() {
	        final JpaTransactionManager transactionManager = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
	        return transactionManager;
	    }

	    @Bean
	    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
	        return new PersistenceExceptionTranslationPostProcessor();
	    }

	    final Properties additionalProperties() {
	        final Properties hibernateProperties = new Properties();
	        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
	        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
	        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "false");

	        return hibernateProperties;
	    }
}
