package com.jijo.module.repository;

import org.springframework.stereotype.Repository;

import com.jijo.module.model.User;

/**
 */
@Repository
public interface UserRepository extends ModuleRepository<User, Long> {

}
