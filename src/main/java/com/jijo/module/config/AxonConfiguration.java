package com.jijo.module.config;

import javax.annotation.PostConstruct;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Axon framework configuration
 */
@Configuration
public class AxonConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AxonConfiguration.class);

    @Bean
    CommandBus axonCommandBus() {
        CommandBus bus = new SimpleCommandBus();
        return bus;
    }

    @Bean
    EventBus axonEventBus() {
        SimpleEventBus bus = new SimpleEventBus();
        return bus;
    }

    @Bean
    AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
        AnnotationCommandHandlerBeanPostProcessor p = new AnnotationCommandHandlerBeanPostProcessor();
        p.setCommandBus(axonCommandBus());
        return p;
    }

    @Bean
    AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
        AnnotationEventListenerBeanPostProcessor p = new AnnotationEventListenerBeanPostProcessor();
        p.setEventBus(axonEventBus());
        return p;
    }

    @PostConstruct
    public void postConstruct() {
        log.info("Axon Configuration loaded..");
    }

}
