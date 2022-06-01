package com.polytech.sportbook.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateOfCreation;
    private LocalDateTime dateTimeOfStart;
    private RESERVE_STATUS reserveStatus;
    private PAY_STATUS payStatus;
    private Double hours;
    private Integer sum;
    private Date dateOfPayment;

    @ManyToOne
    @JoinColumn(name = "obj_id", referencedColumnName = "id")
    private SportObject object;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}




