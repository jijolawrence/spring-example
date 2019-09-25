package com.jijo.module.web.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.jijo.module.config.ModuleConfiguration;
import com.jijo.module.config.ModuleSettings;

@Configuration
@EnableConfigurationProperties({ModuleSettings.class})
@Import({ModuleConfiguration.class})

@EnableAutoConfiguration(exclude = {
        SecurityAutoConfiguration.class})
@EnableCaching
public class ModuleWebConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleWebConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("Module Web Configuration loaded...");
    }
}
