package com.polytech.sportbook.domain;

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
                String userName, LocalDateTime registeredAt, String password, Collection<Role> roles){
        this.firstName = firstName;
        this.lastName = lastName;
        this.parentName = parentName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userName = userName;
        this.registeredAt = registeredAt;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Order> orders = new HashSet<>();
}
