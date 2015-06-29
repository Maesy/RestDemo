package hu.vanio.restdemo.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 *
 * @author meszaros.andras
 */
@Configuration
@EnableMongoRepositories
@Profile("mongo")
public class MongoDBConfig {
    
    @Autowired
    private MongoProperties properties;
    
    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        String database = this.properties.getDatabase();
        return new SimpleMongoDbFactory(new MongoClient(), database);
    }
    
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
    
}
