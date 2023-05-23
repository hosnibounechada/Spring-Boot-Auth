package com.hb.auth.mongo;

import com.hb.auth.model.mongo.Product;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ChangeStreamListener implements ApplicationListener<MongoMappingEvent<?>> {
    //private static final Logger logger = LoggerFactory.getLogger(ChangeStreamListener.class);
    @Override
    public void onApplicationEvent(@NotNull MongoMappingEvent<?> event) {
        if(event instanceof AfterSaveEvent<?>){
            log.info("Change Stream Event AfterInsert: {}", event.getSource());
        }
    }
}
