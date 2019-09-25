package com.jijo.module.config.gateway;

import com.jijo.module.command.CreateUserCommand;
import com.jijo.module.command.SearchUserCommand;
import com.jijo.module.command.UpdateUserCommand;
import com.jijo.module.dto.UserSearchResultDto;
import com.jijo.module.exception.ModuleRuntimeException;
import com.jijo.module.model.User;

/**
 * @author jijo.lawrence
 *
 */
public interface ModuleGateway {

    User createUser(CreateUserCommand command) throws ModuleRuntimeException;

    User updateUser(UpdateUserCommand command) throws ModuleRuntimeException;

    UserSearchResultDto searchUser(SearchUserCommand command) throws ModuleRuntimeException;

}
