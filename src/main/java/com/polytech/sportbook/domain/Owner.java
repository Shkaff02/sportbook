package com.polytech.sportbook.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owners")
@Data
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickName;
    private String email;
    private String phoneNumber;
    private String lastName;
    private String firstName;
    private String parentName;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private Set<SportObject> objects = new HashSet<>();
}
