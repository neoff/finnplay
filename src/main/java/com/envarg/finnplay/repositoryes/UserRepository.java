package com.envarg.finnplay.repositoryes;

import com.envarg.finnplay.entitys.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  User findByEmail(String email);
}
