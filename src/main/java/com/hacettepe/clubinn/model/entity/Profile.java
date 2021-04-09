package com.hacettepe.clubinn.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "userprofile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "about", length = 200)
    private String about;

    @Column(name = "hobbies", length = 200)
    private String hobbies;


}
