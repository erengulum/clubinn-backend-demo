package com.hacettepe.clubinn.model.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

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

    @Column(name = "email", length = 100,unique=true)
    private String email;

    @ManyToOne
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
    private Role role;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Collection<Message> Message;

    public User(Long id){
        this.id = id;
    }

}