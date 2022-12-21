package com.envarg.finnplay.services;

import com.envarg.finnplay.dto.UserDto;
import com.envarg.finnplay.entitys.User;
import com.envarg.finnplay.entitys.UserDetail;
import com.envarg.finnplay.repositoryes.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Setter
@Getter
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    
  }
  
  @Override
  public UserDetails loadUserByUsername(String email)
    throws UsernameNotFoundException {
    User user = findUserByEmail(email);
    
    if (user == null) {
      throw new UsernameNotFoundException("Could not find user");
    }
    
    return new UserDetail(user);
  }
  
  @Override
  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }
  
  @Transactional
  @Override
  public void saveUser(UserDto userDto) {
    saveUser(userDto, new User());
  }
  
  //update existing user
  @Transactional
  public void updateUser(UserDto userDto) {
    saveUser(userDto, userRepository.findByEmail(userDto.getEmail()));
  }
  
  private void saveUser(UserDto userDto, User user) {
    if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
      user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    }
    user.setEmail(userDto.getEmail());
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setBirthday(userDto.getBirthday());
    userRepository.save(user);
  }
  
  
  public UserDto getUser(String email) {
    User user = findUserByEmail(email);
    UserDto userDto = new UserDto();
    userDto.setEmail(user.getEmail());
    userDto.setFirstName(user.getFirstName());
    userDto.setLastName(user.getLastName());
    userDto.setBirthday(user.getBirthday());
    return userDto;
  }
}
