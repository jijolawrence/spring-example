package com.jijo.module.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jijo.module.command.CreateUserCommand;
import com.jijo.module.command.SearchUserCommand;
import com.jijo.module.command.UpdateUserCommand;
import com.jijo.module.config.gateway.ModuleGateway;
import com.jijo.module.dto.UserSearchResultDto;

/**
 * @author jijo.lawrence
 *
 */
@RestController
public class UserService {

    @Autowired
    private ModuleGateway gateway;

    @RequestMapping("/")
    public String home() {
        return "Welcome!";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_UTF8_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody Object createUser(@RequestBody CreateUserCommand command) {
        return gateway.createUser(command);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_UTF8_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody Object updateUser(@RequestBody UpdateUserCommand command) {
        return gateway.updateUser(command);
    }

    @RequestMapping(value = "/searchUser", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_UTF8_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody UserSearchResultDto searchUser(@RequestBody SearchUserCommand command) {
        return gateway.searchUser(command);
    }

}
