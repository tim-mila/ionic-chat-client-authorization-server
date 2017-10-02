package com.alimmit.ionic.chatclientauthorization.configuration.data;

import com.alimmit.ionic.chatclientauthorization.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaAuditing
@Profile({Constants.SPRING_PROFILE_DEFAULT})
public class DataSourceConfiguration {

    private static final Log LOG = LogFactory.getLog(DataSourceConfiguration.class);
    private static final String HBM2DDL = "validate";
    private static final String DIALECT = "org.hibernate.dialect.PostgreSQL94Dialect";
    private static final String SCHEMA = "chat";

    @Bean(initMethod = "migrate")
    public Flyway flyway(final DataSource dataSource) {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setSqlMigrationPrefix("psql-");
        flyway.setSqlMigrationSeparator("__");
        flyway.setSchemas(SCHEMA);
        return flyway;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource) {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(false);

        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(Constants.ENTITY_PACKAGES);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    private Properties additionalProperties() {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", HBM2DDL);
        properties.setProperty("hibernate.dialect", DIALECT);
        properties.setProperty("hibernate.default_schema", SCHEMA);
        return properties;
    }
}
