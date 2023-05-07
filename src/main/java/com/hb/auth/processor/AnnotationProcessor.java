package com.hb.auth.processor;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.io.IOException;
import java.lang.annotation.Annotation;

public interface AnnotationProcessor<T extends Annotation> {
    boolean process(ConditionContext context, AnnotatedTypeMetadata metadata, AnnotationAttributes attributes) throws IOException;
}