package com.jijo.module.config;

import javax.annotation.PostConstruct;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.GatewayProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jijo.module.command.handler.CreateUserCommandHandler;
import com.jijo.module.command.handler.SearchUserCommandHandler;
import com.jijo.module.command.handler.UpdateUserCommandHandler;
import com.jijo.module.config.gateway.ModuleGateway;

@Configuration
@PropertySource(name = "spring.config.location", value = "classpath:/config/module-config.properties")
@EnableConfigurationProperties({ModuleSettings.class})
@EnableJpaRepositories("com.jijo.module.*")
@ComponentScan("com.jijo.module.*")
@EntityScan("com.jijo.module.*")
@Import({
        AxonConfiguration.class, SwaggerConfiguration.class
})
public class ModuleConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("Module Configuration loaded..");
    }

    @Bean
    ModuleGateway moduleGateway(final CommandBus commandBus) {
        final GatewayProxyFactory factory = new GatewayProxyFactory(commandBus);
        return factory.createGateway(ModuleGateway.class);
    }

    @Bean
    CreateUserCommandHandler createUserCommandHandler() {
        return new CreateUserCommandHandler();
    }

    @Bean
    UpdateUserCommandHandler updateUserCommandHandler() {
        return new UpdateUserCommandHandler();
    }

    @Bean
    SearchUserCommandHandler searchUserCommandHandler() {
        return new SearchUserCommandHandler();
    }

    /*
     * @Bean UserService userService() { return new UserService(); }
     */

}
