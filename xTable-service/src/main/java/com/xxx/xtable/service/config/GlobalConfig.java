package com.xxx.xtable.service.config;


import com.mongodb.MongoClientURI;
import com.xxx.xtable.service.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@PropertySource("classpath:app-${xTable.profile}.properties")
public class GlobalConfig {
    @Value("${mongodb.uri}")
    private String MONGODB_URI;

    @Value("${secret.key}")
    private String SECRET_KEY;

    @Bean
    public MongoDbFactory mongoDbFactory() {
        MongoClientURI mongoClientURI = new MongoClientURI(MONGODB_URI);
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClientURI);

        return mongoDbFactory;
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
        try {
            log.info("init mongoTemplate connected -----------------");
            return new MongoTemplate(mongoDbFactory);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Bean
    public SecretKey secretKey() {
        return new SecretKey(SECRET_KEY);
    }


}
