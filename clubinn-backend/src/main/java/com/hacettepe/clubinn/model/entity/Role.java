package com.hacettepe.clubinn.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    //Her role bir id'ye sahip: Simdilik iki ana rol var: Fizyoterapist ve Hasta
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", length = 100,unique=true)
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Collection<User> users;
}