package ro.RecognitionApp.Server;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"ro.RecognitionApp.Server.FingerPrint", "ro.RecognitionApp.Server.Person"})
@EnableSwagger2
@EnableAutoConfiguration
@PersistenceContext
@EnableTransactionManagement
public class RecognitionApp {

	public static void main(String[] args) {
		SpringApplication.run(RecognitionApp.class, args);
	}
	
	@Bean
	public DataSource dataSource() throws IOException{
        Properties dsProps= PropertiesLoaderUtils.loadAllProperties("dataSource.properties");
        Properties hikariProps=PropertiesLoaderUtils.loadAllProperties("hikari.properties");
        hikariProps.put("dataSourceProperties",dsProps);
        return new HikariDataSource(new HikariConfig(hikariProps));
	}
	
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("ro.RecognitionApp.Server");
        System.out.println("123");
        return entityManagerFactoryBean;
    }
    
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    //for swagger: http://localhost:8085/swagger-ui.html
    //
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("ro.RecognitionApp.Server"))
                .paths(PathSelectors.any())
                .build();
    }
}
