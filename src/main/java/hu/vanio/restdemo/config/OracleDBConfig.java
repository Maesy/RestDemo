package hu.vanio.restdemo.config;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author meszaros.andras
 */
@Configuration
@Profile("oracle")
public class OracleDBConfig {
    
    @Bean(name = "myDataSource")
    @ConfigurationProperties(prefix="spring.datasource.restdemo")
    public DataSource demoDataSource() {
        return DataSourceBuilder.create().build();
    }
    
}
