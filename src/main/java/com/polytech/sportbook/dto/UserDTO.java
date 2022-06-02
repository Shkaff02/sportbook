package com.polytech.sportbook.dto;

import com.polytech.sportbook.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String parentName;
    private String phoneNumber;
    private String email;
    private String userName;
    
    private Collection<Role> roles;

}
