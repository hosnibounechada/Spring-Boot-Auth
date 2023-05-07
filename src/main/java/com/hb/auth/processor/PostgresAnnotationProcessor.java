package com.hb.auth.processor;

import com.hb.auth.annotation.conditional.ConditionalOnPostgres;
import org.hibernate.dialect.PostgreSQLDialect;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.io.IOException;

public class PostgresAnnotationProcessor implements AnnotationProcessor<ConditionalOnPostgres> {
    @Override
    public boolean process(ConditionContext context, AnnotatedTypeMetadata metadata,
                           AnnotationAttributes attributes) throws IOException {
        String dialect = context.getEnvironment().getProperty("spring.jpa.properties.hibernate.dialect");
        return dialect != null && dialect.equals(PostgreSQLDialect.class.getName());
    }
}
