package com.envarg.finnplay.services;

import com.envarg.finnplay.dto.UserDto;
import com.envarg.finnplay.entitys.User;

public interface UserService{
  void saveUser(UserDto userDto);
  
  User findUserByEmail(String email);
  
  UserDto getUser(String email);
  
  void updateUser(UserDto userDto);
}
