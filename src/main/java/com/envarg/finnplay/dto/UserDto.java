package com.envarg.finnplay.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

//first name, last name, email, password, and birthday.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private Long id;
  @NotEmpty
  private String firstName;
  @NotEmpty
  private String lastName;
  @NotEmpty(message = "Email shouldn't be empty")
  @Email
  private String email;
  @NotEmpty(message = "Password shouldn't be empty")
  private String password;
  
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonFormat(pattern="yyyy-MM-dd")
  @NonNull
  @Past(message = "Birthday should be in the past")
  private LocalDate birthday;
}
