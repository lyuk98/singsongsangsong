package com.ssafy.singsongsangsong.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	// 아래에 mariaDB를 사용하는 entity를 설정합니다.
	basePackages = {"com.ssafy.singsongsangsong.repository.maria"},
	entityManagerFactoryRef = "mariaDbEntityManagerFactory",
	transactionManagerRef = "mariaDbTransactionManager"
)
public class MariaDbDataSourceConfig {

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;

	@Primary
	@Bean(name = "mariaDbProperties")
	@ConfigurationProperties(prefix = "spring.datasource.mariadb")
	public DataSourceProperties mariaDbProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "mariaDbDataSource")
	public DataSource mariaDbDataSource(
		@Qualifier(value = "mariaDbProperties") DataSourceProperties mariaDbProperties) {
		return mariaDbProperties().initializeDataSourceBuilder().build();
	}

	@Bean(name = "mariaDbEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
		@Qualifier("mariaDbDataSource") DataSource mariaDbDataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(mariaDbDataSource);
		em.setPackagesToScan("com.ssafy.singsongsangsong.domain");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setJpaPropertyMap(Map.of("hibernate.hbm2ddl.auto", ddlAuto));
		return em;
	}

	@Bean(name = "mariaDbTransactionManager")
	@Primary
	public PlatformTransactionManager transactionManager(
		@Qualifier("mariaDbEntityManagerFactory") EntityManagerFactory entityManagerFactory
	) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
