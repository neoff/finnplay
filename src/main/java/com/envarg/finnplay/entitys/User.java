package com.envarg.finnplay.entitys;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//first name, last name, email, password, and birthday.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name" , nullable = false)
    private String firstName;
    
    @Column(name = "last_name" , nullable = false)
    private String lastName;
    @Column(unique = true , nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    
    private String role = "ROLE_USER";
    
    @Column(nullable = false)
    private LocalDate birthday;
    private boolean enabled;
}
