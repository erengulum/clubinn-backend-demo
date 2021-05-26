package com.hacettepe.clubinn.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public User(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "uname", length = 100, unique = true)
    private String username;

    @Column(name = "pwd", length = 200)
    private String password;

    @Column(name = "firstname", length = 200)
    private String firstName;

    @Column(name = "surname", length = 200)
    private String surname;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @ManyToOne
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")})
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Profile profile;


    @ManyToMany(mappedBy = "userList")
    private Collection<Chat> chat;


    @ManyToMany(mappedBy = "members")
    private Collection<SubClub> subclubs;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Message> Message;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<SubClub> administrations;

}