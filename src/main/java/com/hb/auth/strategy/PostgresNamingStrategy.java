package com.hb.auth.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class PostgresNamingStrategy implements PhysicalNamingStrategy {
    private final PhysicalNamingStrategy delegate = new PhysicalNamingStrategyStandardImpl();
    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return null;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return null;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return null;
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return null;
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        if (context.getDialect() instanceof PostgreSQLDialect && "last_name".equalsIgnoreCase(name.getText())) {
            return Identifier.toIdentifier("upper(" + name.getText() + ")", name.isQuoted());
        }
        return delegate.toPhysicalColumnName(name, context);
    }
}