package com.envarg.finnplay.controllers;

import java.security.Principal;
import com.envarg.finnplay.dto.UserDto;
import com.envarg.finnplay.entitys.User;
import com.envarg.finnplay.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
  private UserService userService;
  
  public UserController(UserService userService) {this.userService = userService;}
  
  //user information
  @GetMapping("/user")
  public String userForm(Model model, Principal principal) {
    model.addAttribute("user", userService.getUser(principal.getName()));
    return "signup";
  }
  
  @GetMapping("/signup")
  public String signup(Model model) {
    UserDto user = new UserDto();
    model.addAttribute("user", user);
    return "signup";
  }
  @PostMapping("/signup")
  public String registration(@ModelAttribute("user") @Valid UserDto userDto,
                             BindingResult result,
                             Model model,
                             Principal principal){
    if(principal != null && principal.getName() != null){
      if(result.hasErrors()){
        model.addAttribute("user", userDto);
        return "/user";
      }
      userService.updateUser(userDto);
      model.addAttribute("user", userService.getUser(principal.getName()));
      return "signup";
    }
    User existingUser = userService.findUserByEmail(userDto.getEmail());
    
    if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
      result.rejectValue("email", null,
        "email exist");
    }
    
    if(result.hasErrors()){
      model.addAttribute("user", userDto);
      return "/signup";
    }
    
    userService.saveUser(userDto);
    return "redirect:/signup?success";
  }
}
