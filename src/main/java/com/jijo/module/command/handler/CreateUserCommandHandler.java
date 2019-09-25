package com.jijo.module.command.handler;

import javax.transaction.Transactional;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jijo.module.command.CreateUserCommand;
import com.jijo.module.model.User;
import com.jijo.module.repository.UserRepository;

/**
 * @author jijo.lawrence
 *
 */
public class CreateUserCommandHandler extends AbstractCommandHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserCommandHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @CommandHandler
    public User handle(final CreateUserCommand cmd) {
        LOGGER.debug("Creating User: {0}", cmd.getFirstName());
        User user = new User(cmd.getFirstName(), cmd.getLastName());
        userRepository.saveAndFlush(user);
        LOGGER.debug("User created: {0}", cmd.getFirstName());
        return user;
    }
}
