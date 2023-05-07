package com.hb.auth.condition;

import org.hibernate.dialect.PostgreSQLDialect;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class PostgresCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String dialect = context.getEnvironment().getProperty("spring.jpa.properties.hibernate.dialect");
        System.out.println();
        return dialect != null && dialect.equals(PostgreSQLDialect.class.getName());
    }
}
