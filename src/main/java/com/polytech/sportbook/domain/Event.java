package com.polytech.sportbook.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime dateTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "obj_id", referencedColumnName = "id")
    private SportObject sportObject;
}
