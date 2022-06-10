package com.polytech.sportbook.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "objects")
@Data
@NoArgsConstructor
public class SportObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String city;
    private String district;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Category> categories = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "object", fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "own_id", referencedColumnName = "id")
    private Owner owner;

    @OneToMany
    private Set<Event> events = new HashSet<>();
}
