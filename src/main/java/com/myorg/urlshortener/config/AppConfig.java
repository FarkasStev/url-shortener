package com.myorg.urlshortener.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@ComponentScan(
    basePackages = "com.myorg.urlshortener",
    excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Controller.class)
)
public class AppConfig {
    
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds) {
    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    emf.setDataSource(ds);
    emf.setPackagesToScan("com.myorg.urlshortener.model");
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setDatabase(Database.POSTGRESQL);
    emf.setJpaVendorAdapter(adapter);

    Map<String, Object> props = new HashMap<>();

    // Explicit Hibernate config
    props.put("hibernate.hbm2ddl.auto", "update");
    props.put("hibernate.show_sql", "true");

    emf.setJpaPropertyMap(props);

    return emf;
  }

  @Bean
  public DataSource dataSource() {
    Map<String, String> env = System.getenv();
    String host     = env.getOrDefault("DB_HOST", "localhost");
    String port     = env.getOrDefault("DB_PORT", "5432");
    String name     = env.getOrDefault("DB_NAME", "urlshortener");
    String user     = env.getOrDefault("DB_USER", "postgres");
    String password = env.getOrDefault("DB_PASSWORD", "postgres");

    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setUrl("jdbc:postgresql://" + host + ":" + port + "/" + name);
    ds.setDriverClassName("org.postgresql.Driver");
    ds.setUsername(user);
    ds.setPassword(password);
    return ds;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    return new JpaTransactionManager(emf);
  }

  @PostConstruct
  public void init() {
    // TODO: remove this
    System.out.println("AppConfig loaded");
  }
}
