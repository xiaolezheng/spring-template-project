package com.xxx.xtable.service.test;

import com.xxx.xtable.service.config.RootConfig;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
public class TestMain {
    static {
        System.setProperty("xTable.profile", "dev");
    }


    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfig.class);
        MongoTemplate mongoTemplate = ctx.getBean(MongoTemplate.class);

        for (int i = 0; i < 10; i++) {
            ObjectId id = new ObjectId();
            log.info("{},{}", id.toHexString(), id.toString());
        }
    }
}
