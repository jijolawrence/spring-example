package com.jijo.module.command.handler;

import java.util.Optional;

import javax.transaction.Transactional;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jijo.module.command.UpdateUserCommand;
import com.jijo.module.exception.ModuleRuntimeException;
import com.jijo.module.model.User;
import com.jijo.module.repository.UserRepository;

/**
 * @author jijo.lawrence
 *
 */
public class UpdateUserCommandHandler extends AbstractCommandHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateUserCommandHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @CommandHandler
    public User handle(final UpdateUserCommand cmd) {
        LOGGER.debug("Updating User: {0}", cmd.getId());
        Optional<User> userTemp = userRepository.findById(cmd.getId());
        if (userTemp.get() != null) {
            User user = userTemp.get();
            user.setAddress(cmd.getAddress());
            user.setPhone(cmd.getPhone());
            user.setEmail(cmd.getEmail());
            user.setSex(cmd.getSex());
            userRepository.saveAndFlush(user);
            LOGGER.debug("User updated: {0}", cmd.getId());
            return user;
        } else {
            throw new ModuleRuntimeException(String.format("Unable to update. User does not exists: {0}", cmd.getId()));
        }
    }
}
