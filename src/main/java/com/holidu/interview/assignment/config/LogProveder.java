package com.holidu.interview.assignment.config;

import org.slf4j.*;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.*;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Field;

import static java.util.Optional.*;

/**
 * Copied from https://medium.com/simars/inject-loggers-in-spring-java-or-kotlin-87162d02d068.
 * Allows the injection of the correct logger.
 */

@Configuration
public class LogProveder {
    @Bean
    @Scope("prototype")
    public Logger logger(final InjectionPoint ip) {
        return LoggerFactory.getLogger(ofNullable(ip.getMethodParameter())
                .<Class>map(MethodParameter::getContainingClass)
                .orElseGet( () ->
                        ofNullable(ip.getField())
                                .map(Field::getDeclaringClass)
                                .orElseThrow (IllegalArgumentException::new)
                )
        );
    }
}
