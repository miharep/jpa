package web.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = {"web.dao", "web.service", "web.model"})
public class JpaConfig {
    
    private final Environment env;
    
    @Autowired
    public JpaConfig(Environment env) {
        this.env = env;
    }
    
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("javax.persistence.jdbc.driver"));
        dataSource.setUrl(env.getProperty("javax.persistence.jdbc.url"));
        dataSource.setUsername(
                env.getProperty("javax.persistence.jdbc.user"));
        dataSource.setPassword(
                env.getProperty("javax.persistence.jdbc.password"));
        return dataSource;
    }
    
    @Bean
    protected LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf =
                new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(getDataSource());
        
        Properties props=new Properties();
        props.put("javax.persistence.schema-generation.database.action",
                env.getProperty(
                        "javax.persistence.schema-generation.database.action"));
        props.put("hibernate.show_sql",
                env.getProperty("hibernate.show_sql"));
        props.put("hibernate.format_sql",
                env.getProperty("hibernate.format_sql"));
        props.put("hibernate.highlight_sql",
                env.getProperty("hibernate.highlight_sql"));
        props.put("hibernate.use_nationalized_character_data",
                env.getProperty("hibernate.use_nationalized_character_data"));
        
        emf.setJpaProperties(props);
        emf.setPackagesToScan("web");
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return emf;
    }
    
    @Bean
    public PlatformTransactionManager transactionManagerManager() {
        return new JpaTransactionManager(getEntityManagerFactory().getObject());
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor
            persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}