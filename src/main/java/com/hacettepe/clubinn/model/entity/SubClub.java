package com.hacettepe.clubinn.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "subclubs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubClub {

    @Id
    @Column(name = "subclub_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "imageurl", length = 100, unique = true)
    private String imageurl;

    @Column(name = "subClubName", length = 100, unique = true)
    private String subClubName;

    @Column(name = "description", length = 200)
    private String description;

    //many to one ilişkiyle club'a bağlanacak

    @ManyToOne
    @JoinTable(name = "SUBCLUB_CATEGORY", joinColumns = {
            @JoinColumn(name = "SUBCLUD_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "CLUBCATEGORY_ID")})
    private ClubCategory clubCategory;

    @EqualsAndHashCode.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "chat_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Chat chat;

    @ManyToMany
    @JoinTable(
            name = "subclub_member",
            joinColumns = @JoinColumn(name = "subclub_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> members;

    @OneToMany(mappedBy = "subClub", cascade = CascadeType.ALL)
    private Collection<Announcement> announcements;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "admin_id")
    private User admin;

    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Form form;

    @OneToMany(mappedBy = "ownerSubClub", cascade = CascadeType.ALL)
    private Collection<Feedback> feedbacks;

}
