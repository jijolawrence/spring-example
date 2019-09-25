package com.jijo.module.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "module.config", ignoreUnknownFields = false)
public class ModuleSettings {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleSettings.class);

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("Module Settings loaded...");
    }

}
