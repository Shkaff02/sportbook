package com.polytech.sportbook.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {
    public User(String firstName, String lastName, String parentName, String phoneNumber, String email,
                String userName, String password, Collection<Role> roles){
        this.firstName = firstName;
        this.lastName = lastName;
        this.parentName = parentName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userName = userName;
        this.registeredAt = LocalDateTime.now();
        this.roles = roles;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String parentName;
    private String phoneNumber;
    private String email;
    private String userName;
    private LocalDateTime registeredAt;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Role> roles = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Order> orders = new HashSet<>();
}
