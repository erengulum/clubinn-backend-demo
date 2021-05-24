package com.hacettepe.clubinn.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "chat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "chat_description", length = 100)
    private String chatDescription;

    @Column(name = "creation_date", length = 20)
    private String creationDate;

    @EqualsAndHashCode.Exclude
    @OneToOne( fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "subclub_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SubClub subClub;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Message> messageList;




    @ManyToMany
    @JoinTable(
            name = "chat_user",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> userList;

}